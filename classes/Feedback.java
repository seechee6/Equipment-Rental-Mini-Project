package classes;

public class Feedback extends AbstractFeedback {
    public Feedback(String feedbackId, Customer customer, String message, Supplier supplier) {
        super(feedbackId, customer, message, supplier);
    }

    @Override
    public String getFormattedFeedback() {
        return "Feedback ID: " + feedbackId +
                "\nCustomer: " + customer.getName() +
                "\nMessage: " + message +
                "\nSupplier: " + supplier.getName();
    }
}
