package HospitalManagementSystem.service;

import HospitalManagementSystem.model.Patient;
import HospitalManagementSystem.utils.FileUtils;

public class PatientService {
    public void addPatient(Patient patient) {
        FileUtils.writeToFile("data/patients.txt", patient.toCSV());
    }
}
