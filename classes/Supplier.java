package classes;

import java.util.List;
import java.util.Scanner;

public class Supplier extends User {
    private String supplierId;

    public Supplier(String userId, String name, String email, String supplierId) {
        super(userId, name, email);
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void viewAllFeedbacks() {
        List<AbstractFeedback> allFeedbacks = AbstractFeedback.getAllFeedbacks();

        boolean feedbackFound = false;

        for (AbstractFeedback feedback : allFeedbacks) {
            if (feedback.getSupplier().getSupplierId().equals(this.supplierId)) {
                feedbackFound = true;
                System.out.println(feedback.getFormattedFeedback());
                System.out.println();
            }
        }

        if (!feedbackFound) {
            System.out.println("No feedback found for this supplier.");
        }
    }

    public boolean isValidInput(String equipmentId, String equipmentName, String equipmentStatus,
            double equipmentPrice) {
        if (equipmentId.isEmpty()) {
            System.out.println("Invalid input: Equipment ID cannot be empty.");
            return false;
        }
        if (equipmentName.isEmpty()) {
            System.out.println("Invalid input: Equipment name cannot be empty.");
            return false;
        }
        if (!equipmentStatus.equalsIgnoreCase("available") && !equipmentStatus.equalsIgnoreCase("rented")) {
            System.out.println("Invalid input: Equipment status must be either 'available' or 'rented'.");
            return false;
        }
        if (equipmentPrice <= 0) {
            System.out.println("Invalid input: Equipment price must be a positive number.");
            return false;
        }
        return true;
    }

    @Override
    public User login(List<? extends User> registeredUsers, Scanner scanner) {
        System.out.println("\nSupplier Login:");

        System.out.print("Enter supplier ID: ");
        String inputId = scanner.nextLine().trim();

        Supplier supplier = (Supplier) registeredUsers.stream()
                .filter(u -> u instanceof Supplier && ((Supplier) u).getSupplierId().equals(inputId))
                .findFirst()
                .orElse(null);

        if (supplier != null) {
            System.out.println("Welcome back, " + supplier.getName() + "!");
            return supplier;
        } else {
            System.out.println("Invalid supplier ID. Please register first.");
            return null;
        }
    }
}