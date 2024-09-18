package classes;

import java.util.ArrayList;
import java.util.List;

public class Equipment implements Inventory {
    private String equipmentId;
    private String name;
    private String status;
    private double price;
    private Supplier supplier;
    private List<Equipment> subEquipments;

    public Equipment(String equipmentId, String name, String status, double price, Supplier supplier) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.status = status;
        this.price = price;
        this.supplier = supplier;
        this.subEquipments = new ArrayList<>();
    }

    // Existing getters and setters

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // Implementing Inventory methods

    @Override
    public void addEquipment(Equipment equipment) {
        this.subEquipments.add(equipment);
    }

    @Override
    public void removeEquipment(Equipment equipment) {
        this.subEquipments.remove(equipment);
    }

    @Override
    public List<Equipment> getEquipments() {
        return this.subEquipments;
    }

    @Override
    public Equipment findEquipmentById(String equipmentId) {
        if (this.equipmentId.equals(equipmentId)) {
            return this;
        }
        return this.subEquipments.stream()
                .filter(e -> e.getEquipmentId().equals(equipmentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId='" + equipmentId + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", supplier=" + supplier +
                ", subEquipments=" + subEquipments +
                '}';
    }
}