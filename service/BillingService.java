package service;

import java.util.UUID;
import model.Appointment;
import model.Doctor;
import model.Patient;
import utils.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillingService {
    public String generateBill(Appointment appointment) {
        Patient patient = appointment.getPatient();
        Doctor doctor = appointment.getDoctor();
        String slot = appointment.getSlot();

        double consultationFee = 500.0;
        double serviceCharge = 50.0;
        double total = consultationFee + serviceCharge;

        String billId = UUID.randomUUID().toString();

        String sql = "INSERT INTO bills(id, patient_name, doctor_name, slot, consultation_fee, service_charge, total) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, billId);
            pstmt.setString(2, patient.getName());
            pstmt.setString(3, doctor.getName());
            pstmt.setString(4, slot);
            pstmt.setDouble(5, consultationFee);
            pstmt.setDouble(6, serviceCharge);
            pstmt.setDouble(7, total);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billId;
    }
}
