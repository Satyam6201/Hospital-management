import java.util.Scanner;
import java.util.UUID;
import model.Appointment;
import model.Bill;
import model.Patient;
import service.AppointmentService;
import service.BillingService;
import service.PatientService;
import utils.DatabaseHelper;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("   HOSPITAL MANAGEMENT SYSTEM ");

        // Initialize Database once at startup
        DatabaseHelper.initializeDatabase();

        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        BillingService billingService = new BillingService();

        // 1. Gather Patient Information
        System.out.print("\nEnter Patient Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        
        System.out.print("Enter Symptom (e.g., foot pain, heart, kids): ");
        String symptom = sc.nextLine();

        Patient patient = new Patient(UUID.randomUUID().toString(), name, age, symptom);
        
        if (!patientService.addPatient(patient)) {
            System.out.println("\n[X] Error: Could not save patient to database. Please check your MySQL connection.");
            return;
        }

        // 2. Book Appointment Automatically
        Appointment appointment = appointmentService.bookAppointment(patient);
        
        if (appointment == null) {
            System.out.println("\n[!] Sorry, no available doctors right now for that symptom.");
            return;
        }
        
        System.out.println("\n[✔] Appointment Booked Successfully!");
        System.out.println("    Doctor: " + appointment.getDoctor().getName() + " (" + appointment.getDoctor().getSpecialty() + ")");
        System.out.println("    Time Slot: " + appointment.getSlot());

        // 3. Generate Billing
        Bill bill = billingService.generateBill(appointment);
        System.out.println("\n--- Bill Generated ---");
        System.out.println("    Bill ID: " + bill.getId());
        System.out.println("    Total Amount: $" + bill.getTotalAmount());
        System.out.println("    Status: " + (bill.isPaid() ? "Paid" : "Pending"));

        // 4. Payment Processing
        System.out.print("\nDo you want to pay the bill now? (yes/no): ");
        String payChoice = sc.nextLine();
        
        if (payChoice.equalsIgnoreCase("yes") || payChoice.equalsIgnoreCase("y")) {
            boolean success = billingService.processPayment(bill.getId());
            if (success) {
                System.out.println("\n[💰] Payment Successful! Thank you.");
            } else {
                System.out.println("\n[X] Payment Failed or Bill not found.");
            }
        } else {
            System.out.println("\n[!] Please pay your bill at the front desk before leaving.");
        }
        
        System.out.println("\nThank you for using the Hospital Management System.");
        sc.close();
    }
}