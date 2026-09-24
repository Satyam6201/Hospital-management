package service;

import java.util.List;
import model.Doctor;
import utils.DataStore;

public class DoctorService {
    public static List<Doctor> getAllDoctors() {
        return DataStore.doctors;
    }

    public static void addDoctor(Doctor doctor) {
        DataStore.doctors.add(doctor);
    }

    public static Doctor findDoctorBySymptom(String symptom) {
        for (Doctor d : DataStore.doctors) {
            if (symptom.toLowerCase().contains(d.getSpecialty().toLowerCase()) || d.getSpecialty().equalsIgnoreCase("General")) {
                return d;
            }
        }
        return DataStore.doctors.get(0); // fallback
    }

    public static void updateDoctorSlots(Doctor doctor) {
        // In a simple in-memory list, updating the object instance automatically updates it in the list.
    }
}
