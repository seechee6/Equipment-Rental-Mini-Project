package ui;

import classes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipmentRentalApp {
    private Equipment[] inventory;
    private int inventorySize;
    private static final int MAX_INVENTORY = 100;
    private List<Rental> rentalList;
    private Scanner scanner;
    private List<Customer> registeredCustomers;
    private List<Supplier> registeredSuppliers;

    public EquipmentRentalApp() {
        this.inventory = new Equipment[MAX_INVENTORY];
        this.inventorySize = 0;
        this.rentalList = new ArrayList<>();
        this.registeredCustomers = new ArrayList<>();
        this.registeredSuppliers = new ArrayList<>();
        this.scanner = new Scanner(System.in);

    }

    public void start() {
        System.out.println("Welcome to Equipment Rental Management System");

        while (true) {

            printLoginMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        customerLogin();
                        break;
                    case 2:
                        supplierLogin();
                        break;
                    case 3:
                        registerCustomer();
                        break;
                    case 4:
                        registerSupplier();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter again.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");

            }
        }
    }

    private void printLoginMenu() {
        System.out.println("\nLogin Menu:");
        System.out.println("1. Customer Login");
        System.out.println("2. Supplier Login");
        System.out.println("3. New Customer? Register");
        System.out.println("4. New Supplier? Register");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void customerLogin() {
        User loggedInUser = new Customer(null, null, null, null).login(registeredCustomers, scanner);
        if (loggedInUser != null) {
            customerMenu((Customer) loggedInUser);
        }
    }

    private void supplierLogin() {
        User loggedInUser = new Supplier(null, null, null, null).login(registeredSuppliers, scanner);
        if (loggedInUser != null) {
            supplierMenu((Supplier) loggedInUser);
        }
    }

    private void customerMenu(Customer customer) {
        while (true) {

            printCustomerMenu(customer);

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        customer.rentOrInputRental(inventory, inventorySize, rentalList);
                        break;
                    case 2:
                        listInventory();
                        break;
                    case 3:
                        customer.addFeedback(registeredSuppliers);
                        break;
                    case 4:
                        customer.viewRentalInformation(rentalList);
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter again.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");

            }
        }
    }

    private void printCustomerMenu(Customer customer) {
        System.out.println("\nHELLO " + customer.getName() + " (ID:" + customer.getCustomerId() + ")");
        System.out.println("\nCustomer Menu:");
        System.out.println("1. Rent Equipment");
        System.out.println("2. List Inventory");
        System.out.println("3. Add Feedback");
        System.out.println("4. View rental");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }

    private void supplierMenu(Supplier supplier) {
        while (true) {
            printSupplierMenu(supplier);

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        addEquipment(supplier);
                        break;
                    case 2:
                        deleteEquipment();
                        break;
                    case 3:
                        editEquipment();
                        break;
                    case 4:
                        listInventory();
                        break;
                    case 5:
                        supplier.viewAllFeedbacks();
                        break;
                    case 6:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void printSupplierMenu(Supplier supplier) {
        System.out.println("\nHELLO " + supplier.getName() + " (ID:" + supplier.getSupplierId() + ")");
        System.out.println("\nSupplier Menu:");
        System.out.println("1. Add Equipment");
        System.out.println("2. Delete Equipment");
        System.out.println("3. Edit Equipment");
        System.out.println("4. Check Inventory");
        System.out.println("5. View all feedbacks");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
    }

    private void listInventory() {
        System.out.println("\nAvailable Equipment for Rent:");
        boolean hasEquipment = false;

        for (int i = 0; i < inventorySize; i++) {
            Equipment equipment = inventory[i];
            if ("Available".equalsIgnoreCase(equipment.getStatus())) {
                System.out.println(equipment.getEquipmentId() + ": " + equipment.getName() +
                        " - Price: " + equipment.getPrice() + " - Status: " + equipment.getStatus());
                hasEquipment = true;
            }
        }

        if (!hasEquipment) {
            System.out.println("No equipment found.");
        }
    }

    private void registerCustomer() {
        System.out.println("\nRegister New Customer:");

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine().trim();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine().trim();

        Customer newCustomer = new Customer(customerId, customerName, customerEmail, customerId);
        registeredCustomers.add(newCustomer);

        System.out.println("Customer registered successfully.");
    }

    public void addEquipment(Supplier supplier) {
        if (inventorySize >= MAX_INVENTORY) {
            System.out.println("Inventory is full. Cannot add more equipment.");
            return;
        }

        System.out.println("\nAdd Equipment:");

        System.out.print("Enter equipment ID: ");
        String equipmentId = scanner.nextLine().trim();

        System.out.print("Enter equipment name: ");
        String equipmentName = scanner.nextLine().trim();

        System.out.print("Enter equipment status (available/rented): ");
        String equipmentStatus = scanner.nextLine().trim();

        double equipmentPrice = 0;
        boolean validPrice = false;
        while (!validPrice) {
            System.out.print("Enter equipment price: ");
            try {
                equipmentPrice = Double.parseDouble(scanner.nextLine().trim());
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Please enter a numeric value.");
            }
        }

        if (supplier.isValidInput(equipmentId, equipmentName, equipmentStatus, equipmentPrice)) {
            Equipment equipment = new Equipment(equipmentId, equipmentName, equipmentStatus, equipmentPrice, supplier);
            inventory[inventorySize++] = equipment;
            System.out.println("Equipment added successfully.");
        } else {
            System.out.println("Failed to add equipment due to invalid input.");
        }
    }

    private void deleteEquipment() {
        System.out.println("\nDelete Equipment:");

        System.out.print("Enter equipment ID to delete: ");
        String equipmentId = scanner.nextLine().trim();

        for (int i = 0; i < inventorySize; i++) {
            if (inventory[i].getEquipmentId().equals(equipmentId)) {
                System.arraycopy(inventory, i + 1, inventory, i, inventorySize - i - 1);
                inventorySize--;
                System.out.println("Equipment deleted successfully.");
                return;
            }
        }

        System.out.println("Equipment not found.");
    }

    private void editEquipment() {
        System.out.println("\nEdit Equipment:");

        System.out.print("Enter equipment ID to edit: ");
        String equipmentId = scanner.nextLine().trim();

        for (int i = 0; i < inventorySize; i++) {
            if (inventory[i].getEquipmentId().equals(equipmentId)) {
                System.out.print("Enter new equipment name: ");
                String newName = scanner.nextLine().trim();

                System.out.print("Enter new equipment status: ");
                String newStatus = scanner.nextLine().trim();

                System.out.print("Enter new equipment price: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();

                inventory[i].setName(newName);
                inventory[i].setStatus(newStatus);
                inventory[i].setPrice(newPrice);

                System.out.println("Equipment updated successfully.");
                return;
            }
        }

        System.out.println("Equipment not found.");
    }

    private void registerSupplier() {
        System.out.println("\nRegister New Supplier:");

        System.out.print("Enter supplier ID: ");
        String supplierId = scanner.nextLine().trim();

        System.out.print("Enter supplier name: ");
        String supplierName = scanner.nextLine().trim();

        System.out.print("Enter supplier email: ");
        String supplierEmail = scanner.nextLine().trim();

        Supplier newSupplier = new Supplier(supplierId, supplierName, supplierEmail, supplierId);
        registeredSuppliers.add(newSupplier);

        System.out.println("Supplier registered successfully.");
    }

    public static void main(String[] args) {
        EquipmentRentalApp app = new EquipmentRentalApp();
        app.start();
    }
}