package service;

import model.Doctor;
import utils.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorService {
    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String slots = rs.getString("available_slots");
                List<String> slotList = new ArrayList<>(Arrays.asList(slots.split("\\|")));
                doctors.add(new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialty"),
                        slotList
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // If DB is empty, add a dummy doctor to avoid crash
        if (doctors.isEmpty()) {
            Doctor dummy = new Doctor("d1", "Dr. Smith", "General", new ArrayList<>(Arrays.asList("10:00 AM", "11:00 AM")));
            addDoctor(dummy);
            doctors.add(dummy);
        }
        return doctors;
    }

    public static void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors(id, name, specialty, available_slots) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctor.getId());
            pstmt.setString(2, doctor.getName());
            pstmt.setString(3, doctor.getSpecialty());
            pstmt.setString(4, String.join("|", doctor.getAvailableSlots()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Doctor findDoctorBySymptom(String symptom) {
        List<Doctor> doctors = getAllDoctors();
        for (Doctor d : doctors) {
            if (symptom.toLowerCase().contains(d.getSpecialty().toLowerCase())) {
                return d;
            }
        }
        return doctors.get(0); // fallback
    }

    public static void updateDoctorSlots(Doctor doctor) {
        String sql = "UPDATE doctors SET available_slots = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.join("|", doctor.getAvailableSlots()));
            pstmt.setString(2, doctor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
