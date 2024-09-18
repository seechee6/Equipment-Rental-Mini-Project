package classes;

public class Invoice {
    private String invoiceId;
    private Payment payment;

    public Invoice(String invoiceId, Payment payment) {
        this.invoiceId = invoiceId;
        this.payment = payment;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
