package utils;

import classes.Payment;
import classes.Invoice;

public class Utils {
    public static void generateInvoice(Payment payment) {
        Invoice invoice = new Invoice("I" + System.currentTimeMillis(), payment);
        System.out.println("Invoice generated: " + invoice.getInvoiceId());
    }
}
