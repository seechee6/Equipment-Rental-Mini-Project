package classes;

import java.time.LocalDate;

public class Rental {
    private Customer customer;
    private Equipment equipment;
    private Payment payment;
    private LocalDate rentalDate;

    public Rental(Customer customer, Equipment equipment) {
        this.customer = customer;
        this.equipment = equipment;

        this.rentalDate = LocalDate.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "customer=" + customer +
                ", equipment=" + equipment +
                ", payment=" + payment +
                ", rentalDate=" + rentalDate +
                '}';
    }
}
