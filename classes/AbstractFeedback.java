package classes;

import java.util.ArrayList;
import java.util.List;

// Abstract class
public abstract class AbstractFeedback {
    protected String feedbackId;
    protected Customer customer;
    protected String message;
    protected Supplier supplier;
    protected static List<AbstractFeedback> feedbacks = new ArrayList<>();

    public AbstractFeedback(String feedbackId, Customer customer, String message, Supplier supplier) {
        this.feedbackId = feedbackId;
        this.customer = customer;
        this.message = message;
        this.supplier = supplier;
    }

    // Abstract method
    public abstract String getFormattedFeedback();

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getSupplierName() {
        return supplier.getName();
    }

    // Static methods for feedback management
    public static void addFeedback(AbstractFeedback feedback) {
        feedbacks.add(feedback);
    }

    public static void removeFeedback(AbstractFeedback feedback) {
        feedbacks.remove(feedback);
    }

    public static List<AbstractFeedback> getAllFeedbacks() {
        return feedbacks;
    }
}

