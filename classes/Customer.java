package classes;

import java.util.*;
import java.time.LocalDate;
import utils.Utils;

public class Customer extends User {
    private String customerId;
    private List<Rental> rentalList;
    private Scanner scanner = new Scanner(System.in);

    public Customer(String userId, String name, String email, String customerId) {
        super(userId, name, email);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }

    public void addFeedback(List<Supplier> suppliers) {
        System.out.print("Enter feedback ID: ");
        String feedbackId = scanner.nextLine().trim();

        System.out.print("Enter feedback message: ");
        String message = scanner.nextLine().trim();

        System.out.print("Enter supplier ID: ");
        String supplierId = scanner.nextLine().trim();

        Supplier supplier = null;
        for (Supplier s : suppliers) {
            if (s.getSupplierId().equals(supplierId)) {
                supplier = s;
                break;
            }
        }

        if (supplier != null) {
            Feedback feedback = new Feedback(feedbackId, this, message, supplier);
            AbstractFeedback.addFeedback(feedback);
            System.out.println("Feedback added successfully.");
        } else {
            System.out.println("Supplier not found. Feedback not added.");
        }
    }

    public void rentOrInputRental(Equipment[] inventory, int inventorySize, List<Rental> rentalList) {
        List<Equipment> selectedEquipmentList = new ArrayList<>();
        System.out.println("\nRent Equipment or Input Rental Information:");

        while (true) {
            Equipment selectedEquipment = selectEquipment(inventory, inventorySize);

            if (selectedEquipment != null) {
                selectedEquipmentList.add(selectedEquipment);
                System.out.println("Equipment added to the rental list.");
                System.out.print("Do you want to add more equipment? (y/n): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (!choice.equals("y")) {
                    break;
                }
            } else {
                System.out.println("No more available equipment.");
                break;
            }
        }

        if (!selectedEquipmentList.isEmpty()) {
            System.out.print("Enter rental date (YYYY-MM-DD), or press Enter for today's date: ");
            String dateInput = scanner.nextLine().trim();
            LocalDate rentalDate = dateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(dateInput);

            for (Equipment equipment : selectedEquipmentList) {
                Rental rental = new Rental(this, equipment);
                rental.setRentalDate(rentalDate);
                if (!rentalList.contains(rental)) { // Check for duplicate rentals
                    rentalList.add(rental);
                }
                equipment.setStatus("Rented");
            }
            processPayment(selectedEquipmentList, rentalList);
        } else {
            System.out.println("No equipment selected for rental.");
        }
    }

    private Equipment selectEquipment(Equipment[] inventory, int inventorySize) {
        System.out.println("\nAvailable Equipment:");
        boolean hasAvailableEquipment = false;

        for (int i = 0; i < inventorySize; i++) {
            Equipment equipment = inventory[i];
            if ("Available".equalsIgnoreCase(equipment.getStatus())) {
                System.out.println(equipment.getEquipmentId() + ": " + equipment.getName() +
                        " - Price: " + equipment.getPrice() + " - Status: " + equipment.getStatus());
                hasAvailableEquipment = true;
            }
        }

        if (!hasAvailableEquipment) {
            System.out.println("No available equipment.");
            return null;
        }

        System.out.print("Enter equipment ID to rent: ");
        String equipmentId = scanner.nextLine().trim();

        for (int i = 0; i < inventorySize; i++) {
            Equipment equipment = inventory[i];
            if (equipment.getEquipmentId().equals(equipmentId) && "Available".equalsIgnoreCase(equipment.getStatus())) {
                return equipment;
            }
        }

        System.out.println("Equipment not found or not available.");
        return null;
    }

    private void processPayment(List<Equipment> selectedEquipmentList, List<Rental> rentalList) {
        System.out.println("\nPayment Summary:");
        double totalAmount = 0;
        for (Equipment equipment : selectedEquipmentList) {
            System.out.println(equipment.getName() + " - Price: " + equipment.getPrice());
            totalAmount += equipment.getPrice();
        }

        System.out.println("Total Amount: " + totalAmount);
        System.out.print("Do you want to proceed with the payment? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
            for (Equipment equipment : selectedEquipmentList) {
                Rental rental = rentalList.stream()
                        .filter(r -> r.getCustomer().equals(this) && r.getEquipment().equals(equipment))
                        .findFirst()
                        .orElse(null);
                if (rental != null) {
                    Payment payment = new Payment(true);
                    rental.setPayment(payment);
                    Utils.generateInvoice(payment);
                }
            }
            System.out.println("Payment confirmed. Equipment rented successfully.");
        } else {
            System.out.println("Payment cancelled. Equipment not rented.");
        }
    }

    public void viewRentalInformation(List<Rental> rentalList) {
        System.out.println("\nYour Rental Information:");

        boolean rentalFound = false;
        for (Rental rental : rentalList) {
            if (rental.getCustomer().getCustomerId().equals(this.customerId)) {
                rentalFound = true;
                System.out.println("Equipment: " + rental.getEquipment().getName());
                System.out.println("Rental Date: " + rental.getRentalDate());
                System.out.println("Total Cost: $" + rental.getEquipment().getPrice());
                System.out.println("Payment Status: "
                        + (rental.getPayment() != null && rental.getPayment().isPaid() ? "Paid" : "Unpaid"));
                System.out.println("--------------------");
            }
        }

        if (!rentalFound) {
            System.out.println("No rentals found for this customer.");
        }
    }

    @Override
    public User login(List<? extends User> registeredUsers, Scanner scanner) {
        System.out.println("\nCustomer Login:");

        System.out.print("Enter customer ID: ");
        String inputId = scanner.nextLine().trim();

        Customer customer = (Customer) registeredUsers.stream()
                .filter(u -> u instanceof Customer && ((Customer) u).getCustomerId().equals(inputId))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            System.out.println("Welcome back, " + customer.getName() + "!");
            return customer;
        } else {
            System.out.println("Invalid customer ID. Please register first.");
            return null;
        }
    }

}