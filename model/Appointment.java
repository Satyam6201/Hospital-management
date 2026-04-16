package HospitalManagementSystem.model;

public class Appointment {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private String slot;

    public Appointment(String id, Patient patient, Doctor doctor, String slot) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getSlot() { return slot; }

    public String toCSV() {
        return id + "," + patient.getName() + "," + doctor.getName() + "," + slot;
    }
}
