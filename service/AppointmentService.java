package service;

import java.util.UUID;
import model.Appointment;
import model.Doctor;
import model.Patient;
import utils.DataStore;

public class AppointmentService {
    public Appointment bookAppointment(Patient patient) {
        Doctor doctor = DoctorService.findDoctorBySymptom(patient.getSymptom());
        String slot = doctor.getAvailableSlots().isEmpty() ? "No slot" : doctor.getAvailableSlots().remove(0);
        
        Appointment appointment = new Appointment(UUID.randomUUID().toString(), patient, doctor, slot);
        DataStore.appointments.add(appointment);

        return appointment;
    }
}
