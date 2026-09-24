package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import model.Appointment;
import model.Doctor;
import model.Patient;
import utils.DatabaseHelper;

public class AppointmentService {
    
    // Books an appointment and returns it. Returns null if no doctors are available.
    public Appointment bookAppointment(Patient patient) {
        Doctor doctor = DoctorService.findDoctorBySymptom(patient.getSymptom());
        
        if (doctor == null || doctor.getAvailableSlots().isEmpty()) {
            return null; // The controller (Main.java) will handle the UI message
        }

        String slot = doctor.getAvailableSlots().remove(0);
        DoctorService.updateDoctorSlots(doctor);

        Appointment appointment = new Appointment(UUID.randomUUID().toString(), patient, doctor, slot);
        
        String sql = "INSERT INTO appointments(id, patient_id, doctor_id, slot) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, appointment.getId());
            pstmt.setString(2, patient.getId());
            pstmt.setString(3, doctor.getId());
            pstmt.setString(4, slot);
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            return null;
        }

        return appointment;
    }
}