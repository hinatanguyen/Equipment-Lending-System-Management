/**
 * @author Nguyen Huy Hoang - s4041847
 */

 package service;

 import model.Equipment;
 import java.util.ArrayList;
 import java.util.HashMap;
 
 public interface InventoryManager {
     void addEquipment(Equipment equipment);
     void updateEquipment(int equipmentID, Equipment updatedEquipment);
     void removeEquipment(int equipmentID);
     HashMap<Integer, Equipment> retrieveAllEquipments();
     ArrayList<Equipment> retrieveAvailableEquipments();
 }
 