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
    
    // Fetches all doctors from database
    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String slots = rs.getString("available_slots");
                List<String> slotList = new ArrayList<>(Arrays.asList(slots.split("\\|")));
                doctors.add(new Doctor(rs.getString("id"), rs.getString("name"), rs.getString("specialty"), slotList));
            }
        } catch (Exception ignored) {
            // Silently ignore in this simple app version
        }
        return doctors;
    }

    // Finds the best matching doctor based on patient symptom
    public static Doctor findDoctorBySymptom(String symptom) {
        List<Doctor> doctors = getAllDoctors();
        for (Doctor d : doctors) {
            // Simple keyword matching for symptoms to specialty
            if (symptom.toLowerCase().contains(d.getSpecialty().toLowerCase()) || d.getSpecialty().equalsIgnoreCase("General")) {
                return d;
            }
        }
        return doctors.isEmpty() ? null : doctors.get(0);
    }

    // Updates the available time slots for a doctor after booking
    public static void updateDoctorSlots(Doctor doctor) {
        String sql = "UPDATE doctors SET available_slots = ? WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, String.join("|", doctor.getAvailableSlots()));
            pstmt.setString(2, doctor.getId());
            pstmt.executeUpdate();
            
        } catch (Exception ignored) {
            // Silently ignore in this simple app version
        }
    }
}