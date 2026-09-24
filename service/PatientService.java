package service;

import model.Patient;
import utils.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatientService {
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patients (id, name, age, symptom) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patient.getId());
            pstmt.setString(2, patient.getName());
            pstmt.setInt(3, patient.getAge());
            pstmt.setString(4, patient.getSymptom());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error adding patient: " + e.getMessage());
        }
    }
}