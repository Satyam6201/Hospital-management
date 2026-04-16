package HospitalManagementSystem.model;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String symptom;

    public Patient(String id, String name, int age, String symptom) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptom = symptom;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSymptom() { return symptom; }

    public String toCSV() {
        return id + "," + name + "," + age + "," + symptom;
    }
}

