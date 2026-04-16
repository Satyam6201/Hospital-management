package HospitalManagementSystem;

import HospitalManagementSystem.model.*;
import HospitalManagementSystem.service.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Hospital Management System ===");

        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Symptom: ");
        String symptom = sc.nextLine();

        Patient patient = new Patient(UUID.randomUUID().toString(), name, age, symptom);
        patientService.addPatient(patient);

        Appointment appointment = appointmentService.bookAppointment(patient);
        System.out.println("\n[✔] Appointment Booked!");
        System.out.println("Doctor: " + appointment.getDoctor().getName());
        System.out.println("Slot: " + appointment.getSlot());
    }
}
