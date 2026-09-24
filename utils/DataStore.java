package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Appointment;
import model.Bill;
import model.Doctor;
import model.Patient;

public class DataStore {
    public static List<Patient> patients = new ArrayList<>();
    public static List<Doctor> doctors = new ArrayList<>();
    public static List<Appointment> appointments = new ArrayList<>();
    public static List<Bill> bills = new ArrayList<>();

    static {
        // Pre-populate some doctors for the simple app
        doctors.add(new Doctor("d1", "Dr. Smith", "Orthopedic", new ArrayList<>(Arrays.asList("10:00 AM", "11:00 AM"))));
        doctors.add(new Doctor("d2", "Dr. Jones", "Cardiologist", new ArrayList<>(Arrays.asList("09:00 AM", "01:00 PM"))));
        doctors.add(new Doctor("d3", "Dr. Adams", "General", new ArrayList<>(Arrays.asList("02:00 PM", "04:00 PM"))));
    }
}
