# Hospital Management System

A Java-based console application designed to manage patients, doctors, appointments, and billing. The system utilizes **MySQL** for robust data storage and retrieval.

## 📂 Project Structure

```text
HospitalManagementSystem/
│
├── model/
│   ├── Appointment.java      # Represents an Appointment
│   ├── Bill.java             # Represents a Billing Receipt
│   ├── Doctor.java           # Represents a Doctor
│   └── Patient.java          # Represents a Patient
│
├── service/
│   ├── AppointmentService.java # Handles booking appointments
│   ├── BillingService.java     # Handles bill generation
│   ├── DoctorService.java      # Handles fetching and updating doctors
│   └── PatientService.java     # Handles adding patients
│
├── utils/
│   └── DatabaseHelper.java     # Manages MySQL Database connection & table creation
│
├── Main.java                   # Main entry point of the application
├── mysql-connector.jar         # MySQL JDBC Driver (Required for database connection)
└── README.md                   # Project documentation
```

## ⚙️ Prerequisites

1. **Java Development Kit (JDK):** Ensure you have JDK 8 or higher installed.
2. **MySQL Server:** You must have MySQL installed and running locally on port 3306.
3. **MySQL Connector:** The `mysql-connector.jar` is required in the project root to allow Java to communicate with MySQL.

## 🚀 Setup & Execution

### 1. Database Configuration
By default, the application connects to a local MySQL server with:
- **Username:** `root`
- **Password:** `root` (Change this in `utils/DatabaseHelper.java` if your password is different).

The application automatically creates a database named `hospital_db` and all necessary tables (`patients`, `doctors`, `appointments`, `bills`) when you run it for the first time.

### 2. Compiling the Code
Open your terminal (PowerShell or Command Prompt), navigate to the `HospitalManagementSystem` directory, and run:
```powershell
javac Main.java model/*.java service/*.java utils/*.java
```

### 3. Running the Application
Since the application depends on the external MySQL driver, you cannot just use `java Main`. You must include the JDBC driver in the classpath using the `-cp` flag:
```powershell
java -cp ".;mysql-connector.jar" Main
```
*(Note: If you are using macOS or Linux, use `:` instead of `;` for the classpath separator).*

## 🌟 Features
- **Patient Management:** Register new patients with their details and symptoms.
- **Doctor Allocation:** Automatically finds and assigns an available doctor based on the patient's symptoms (e.g., matching "foot pain" to an orthopedic specialist).
- **Appointment Booking:** Automatically books an appointment and allocates a time slot.
- **Billing System:** Generates comprehensive billing receipts including consultation fees and service charges.

## 💻 Sample Output

```text
=== Hospital Management System ===
Enter Patient Name: Satyam
Enter Age: 21
Enter Symptom: foot pain

[✔] Appointment Booked!
Doctor: Dr. Smith
Slot: 10:00 AM
```
