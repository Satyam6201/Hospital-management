package service;

import model.Patient;
import utils.DataStore;

public class PatientService {
    public void addPatient(Patient patient) {
        DataStore.patients.add(patient);
    }
}
