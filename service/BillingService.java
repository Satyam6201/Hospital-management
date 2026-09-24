package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import model.Appointment;
import model.Bill;
import utils.DatabaseHelper;

public class BillingService {
    public Bill generateBill(Appointment appointment) {
        double total = 550.0; // Flat fee for simplicity
        Bill bill = new Bill(UUID.randomUUID().toString(), appointment.getPatient().getName(), appointment.getDoctor().getName(), total, false);

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bills(id, patient_name, doctor_name, total, is_paid) VALUES(?, ?, ?, ?, ?)")) {
            pstmt.setString(1, bill.getId());
            pstmt.setString(2, bill.getPatientName());
            pstmt.setString(3, bill.getDoctorName());
            pstmt.setDouble(4, bill.getTotalAmount());
            pstmt.setBoolean(5, bill.isPaid());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error generating bill: " + e.getMessage());
        }
        return bill;
    }

    public void processPayment(String billId) {
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE bills SET is_paid = true WHERE id = ?")) {
            pstmt.setString(1, billId);
            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("[💰] Payment Successful for Bill ID: " + billId);
            } else {
                System.out.println("[!] Bill not found.");
            }
        } catch (Exception e) {
            System.err.println("Error processing payment: " + e.getMessage());
        }
    }
}