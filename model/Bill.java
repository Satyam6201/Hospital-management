package model;

public class Bill {
    private String id;
    private String patientName;
    private String doctorName;
    private double totalAmount;
    private boolean isPaid;

    public Bill(String id, String patientName, String doctorName, double totalAmount, boolean isPaid) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    public String getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isPaid() { return isPaid; }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }
}