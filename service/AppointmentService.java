package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import model.Appointment;
import model.Doctor;
import model.Patient;
import utils.DatabaseHelper;

public class AppointmentService {
    public Appointment bookAppointment(Patient patient) {
        Doctor doctor = DoctorService.findDoctorBySymptom(patient.getSymptom());
        if (doctor == null || doctor.getAvailableSlots().isEmpty()) {
            System.out.println("No available doctors at the moment.");
            return null;
        }

        String slot = doctor.getAvailableSlots().remove(0);
        DoctorService.updateDoctorSlots(doctor);

        Appointment appointment = new Appointment(UUID.randomUUID().toString(), patient, doctor, slot);
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO appointments(id, patient_id, doctor_id, slot) VALUES(?, ?, ?, ?)")) {
            pstmt.setString(1, appointment.getId());
            pstmt.setString(2, patient.getId());
            pstmt.setString(3, doctor.getId());
            pstmt.setString(4, slot);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error booking appointment: " + e.getMessage());
        }

        return appointment;
    }
}