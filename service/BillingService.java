package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import model.Appointment;
import model.Bill;
import utils.DatabaseHelper;

public class BillingService {
    
    // Flat consultation fee
    private static final double CONSULTATION_FEE = 550.0;

    public Bill generateBill(Appointment appointment) {
        Bill bill = new Bill(UUID.randomUUID().toString(), appointment.getPatient().getName(), appointment.getDoctor().getName(), CONSULTATION_FEE, false);
        String sql = "INSERT INTO bills(id, patient_name, doctor_name, total, is_paid) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, bill.getId());
            pstmt.setString(2, bill.getPatientName());
            pstmt.setString(3, bill.getDoctorName());
            pstmt.setDouble(4, bill.getTotalAmount());
            pstmt.setBoolean(5, bill.isPaid());
            pstmt.executeUpdate();
            
        } catch (Exception ignored) {
            // Ignore for simple console app
        }
        return bill;
    }

    // Returns true if payment was processed successfully
    public boolean processPayment(String billId) {
        String sql = "UPDATE bills SET is_paid = true WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, billId);
            int updated = pstmt.executeUpdate();
            return updated > 0;
            
        } catch (Exception e) {
            return false;
        }
    }
}