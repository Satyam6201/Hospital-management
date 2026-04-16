package HospitalManagementSystem.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.utils.FileUtils;

public class DoctorService {
    public static List<Doctor> getAllDoctors() {
        List<String> lines = FileUtils.readAll("data/doctors.txt");
        List<Doctor> doctors = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            String[] slots = parts[3].split("\\|");
            doctors.add(new Doctor(parts[0], parts[1], parts[2], new ArrayList<>(Arrays.asList(slots))));
        }
        return doctors;
    }

    public static Doctor findDoctorBySymptom(String symptom) {
        List<Doctor> doctors = getAllDoctors();
        for (Doctor d : doctors) {
            if (symptom.toLowerCase().contains(d.getSpecialty().toLowerCase())) {
                return d;
            }
        }
        return doctors.get(0); // fallback doctor
    }
}
