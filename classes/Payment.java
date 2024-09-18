package classes;

import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private double amount;
    private LocalDate date;
    private boolean isPaid;

    public Payment(String paymentId, double amount, LocalDate date, boolean isPaid) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.isPaid = isPaid;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Payment(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", isPaid=" + isPaid +
                '}';
    }
}
