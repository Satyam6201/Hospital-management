import java.util.Scanner;
import java.util.UUID;
import model.Appointment;
import model.Bill;
import model.Patient;
import service.AppointmentService;
import service.BillingService;
import service.DoctorService;
import service.PatientService;
import utils.DatabaseHelper;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Hospital Management System ===");

        // Initialize Database
        DatabaseHelper.initializeDatabase();

        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        BillingService billingService = new BillingService();

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Symptom (e.g., foot pain, heart, kids): ");
        String symptom = sc.nextLine();

        // 1. Add Patient
        Patient patient = new Patient(UUID.randomUUID().toString(), name, age, symptom);
        patientService.addPatient(patient);

        // 2. Book Appointment
        Appointment appointment = appointmentService.bookAppointment(patient);
        if (appointment != null) {
            System.out.println("\n[✔] Appointment Booked!");
            System.out.println("Doctor: " + appointment.getDoctor().getName() + " (" + appointment.getDoctor().getSpecialty() + ")");
            System.out.println("Slot: " + appointment.getSlot());

            // 3. Generate Bill
            Bill bill = billingService.generateBill(appointment);
            System.out.println("\n--- Bill Generated ---");
            System.out.println("Total Amount: $" + bill.getTotalAmount());
            System.out.println("Status: " + (bill.isPaid() ? "Paid" : "Pending"));

            // 4. Payment System
            System.out.print("\nDo you want to pay the bill now? (yes/no): ");
            String payChoice = sc.nextLine();
            if (payChoice.equalsIgnoreCase("yes") || payChoice.equalsIgnoreCase("y")) {
                billingService.processPayment(bill.getId());
            } else {
                System.out.println("Please pay your bill at the reception.");
            }
        }
        
        System.out.println("\nThank you for using the Hospital Management System.");
        sc.close();
    }
}