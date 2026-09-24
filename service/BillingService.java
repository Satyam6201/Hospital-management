package service;

import java.util.UUID;
import model.Appointment;
import model.Bill;
import utils.DataStore;

public class BillingService {
    public String generateBill(Appointment appointment) {
        double consultationFee = 500.0;
        double serviceCharge = 50.0;
        double total = consultationFee + serviceCharge;

        String billId = UUID.randomUUID().toString();
        Bill bill = new Bill(billId, appointment.getPatient().getName(), appointment.getDoctor().getName(), appointment.getSlot(), consultationFee, serviceCharge, total);
        DataStore.bills.add(bill);

        return billId;
    }
}
