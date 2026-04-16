package HospitalManagementSystem.service;

import java.util.UUID;

import HospitalManagementSystem.model.Appointment;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Patient;
import HospitalManagementSystem.utils.FileUtils;

public class BillingService {
    public String generateBill(Appointment appointment) {
        Patient patient = appointment.getPatient();
        Doctor doctor = appointment.getDoctor();
        String slot = appointment.getSlot();

        // Fixed charges (you can modify as needed)
        double consultationFee = 500.0;
        double serviceCharge = 50.0;
        double total = consultationFee + serviceCharge;

        String billId = UUID.randomUUID().toString();

        String billText = billId + "," +
                          patient.getName() + "," +
                          doctor.getName() + "," +
                          slot + "," +
                          consultationFee + "," +
                          serviceCharge + "," +
                          total;

        FileUtils.writeToFile("data/bills.txt", billText);

        return billText;
    }
}
