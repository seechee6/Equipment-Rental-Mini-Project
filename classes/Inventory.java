package classes;

import java.util.List;

public interface Inventory {
    void addEquipment(Equipment equipment);

    void removeEquipment(Equipment equipment);

    List<Equipment> getEquipments();

    Equipment findEquipmentById(String equipmentId);
}
