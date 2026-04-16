package HospitalManagementSystem.model;

public class Bill {
    private String id;
    private String patientName;
    private String doctorName;
    private String appointmentSlot;
    private double consultationFee;
    private double serviceCharge;
    private double totalAmount;

    public Bill(String id, String patientName, String doctorName, String appointmentSlot,
                double consultationFee, double serviceCharge, double totalAmount) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentSlot = appointmentSlot;
        this.consultationFee = consultationFee;
        this.serviceCharge = serviceCharge;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppointmentSlot() {
        return appointmentSlot;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String toCSV() {
        return id + "," + patientName + "," + doctorName + "," + appointmentSlot + "," +
               consultationFee + "," + serviceCharge + "," + totalAmount;
    }

    @Override
    public String toString() {
        return "===============================\n" +
               "         BILL RECEIPT\n" +
               "===============================\n" +
               "Bill ID        : " + id + "\n" +
               "Patient Name   : " + patientName + "\n" +
               "Doctor Name    : " + doctorName + "\n" +
               "Slot           : " + appointmentSlot + "\n" +
               "Consultation   : ₹" + consultationFee + "\n" +
               "Service Charge : ₹" + serviceCharge + "\n" +
               "-------------------------------\n" +
               "Total Amount   : ₹" + totalAmount + "\n" +
               "===============================\n";
    }
}
