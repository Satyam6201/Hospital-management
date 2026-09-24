package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Doctor;
import utils.DatabaseHelper;

public class DoctorService {
    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM doctors")) {
            while (rs.next()) {
                String slots = rs.getString("available_slots");
                List<String> slotList = new ArrayList<>(Arrays.asList(slots.split("\\|")));
                doctors.add(new Doctor(rs.getString("id"), rs.getString("name"), rs.getString("specialty"), slotList));
            }
        } catch (Exception e) {
            System.err.println("Error fetching doctors: " + e.getMessage());
        }
        return doctors;
    }

    public static Doctor findDoctorBySymptom(String symptom) {
        List<Doctor> doctors = getAllDoctors();
        for (Doctor d : doctors) {
            if (symptom.toLowerCase().contains(d.getSpecialty().toLowerCase()) || d.getSpecialty().equalsIgnoreCase("General")) {
                return d;
            }
        }
        return doctors.isEmpty() ? null : doctors.get(0);
    }

    public static void updateDoctorSlots(Doctor doctor) {
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE doctors SET available_slots = ? WHERE id = ?")) {
            pstmt.setString(1, String.join("|", doctor.getAvailableSlots()));
            pstmt.setString(2, doctor.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error updating slots: " + e.getMessage());
        }
    }
}