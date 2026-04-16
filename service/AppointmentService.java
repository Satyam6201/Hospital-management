package HospitalManagementSystem.service;

import java.util.UUID;

import HospitalManagementSystem.model.Appointment;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Patient;
import HospitalManagementSystem.utils.FileUtils;

public class AppointmentService {
    public Appointment bookAppointment(Patient patient) {
        Doctor doctor = DoctorService.findDoctorBySymptom(patient.getSymptom());
        String slot = doctor.getAvailableSlots().remove(0);

        Appointment appointment = new Appointment(UUID.randomUUID().toString(), patient, doctor, slot);
        FileUtils.writeToFile("data/appointments.txt", appointment.toCSV());

        return appointment;
    }
}
