package HospitalManagementSystem.model;

import java.util.List;

public class Doctor {
    private String id;
    private String name;
    private String specialty;
    private List<String> availableSlots;

    public Doctor(String id, String name, String specialty, List<String> slots) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.availableSlots = slots;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public List<String> getAvailableSlots() { return availableSlots; }

    public String toCSV() {
        return id + "," + name + "," + specialty + "," + String.join("|", availableSlots);
    }
}
