/**
 * @author Nguyen Huy Hoang - s4041847
 */

 package service;

 import model.Equipment;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Date;
 import java.io.*;
 import java.text.SimpleDateFormat;
 
 public class InventoryService implements InventoryManager {
     private HashMap<Integer, Equipment> inventory;
     private final String dataFile = "equipments.txt";
     private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
     public InventoryService() {
         inventory = new HashMap<>();
         loadDataFromFile();
     }
 
     @Override
     public void addEquipment(Equipment equipment) {
         inventory.put(equipment.getEquipmentID(), equipment);
         saveDataToFile();
     }
 
     @Override
     public void updateEquipment(int equipmentID, Equipment updatedEquipment) {
         if (inventory.containsKey(equipmentID)) {
             inventory.put(equipmentID, updatedEquipment);
             saveDataToFile();
         } else {
             System.out.println("Equipment with ID " + equipmentID + " not found.");
         }
     }
 
     @Override
     public void removeEquipment(int equipmentID) {
         if (inventory.containsKey(equipmentID)) {
             Equipment eq = inventory.get(equipmentID);
             if ("Out of Service".equalsIgnoreCase(eq.getCondition())) {
                 inventory.remove(equipmentID);
                 saveDataToFile();
             } else {
                 System.out.println("Equipment is not in 'Out of Service' condition.");
             }
         } else {
             System.out.println("Equipment with ID " + equipmentID + " not found.");
         }
     }
 
     @Override
     public HashMap<Integer, Equipment> retrieveAllEquipments() {
         return inventory;
     }
 
     @Override
     public ArrayList<Equipment> retrieveAvailableEquipments() {
         ArrayList<Equipment> available = new ArrayList<>();
         for (Equipment eq : inventory.values()) {
             if ("Available".equalsIgnoreCase(eq.getStatus())) {
                 available.add(eq);
             }
         }
         return available;
     }
 
     private void loadDataFromFile() {
         File file = new File(dataFile);
         if (!file.exists()) {
             createSampleData();
             return;
         }
         try (BufferedReader br = new BufferedReader(new FileReader(file))) {
             String line;
             while ((line = br.readLine()) != null) {
                 String[] parts = line.split(",");
                 if (parts.length >= 5) {
                     int id = Integer.parseInt(parts[0]);
                     String name = parts[1];
                     String status = parts[2];
                     Date purchaseDate = sdf.parse(parts[3]);
                     String condition = parts[4];
                     Equipment eq = new Equipment(id, name, status, purchaseDate, condition);
                     inventory.put(id, eq);
                 }
             }
         } catch (Exception e) {
             System.out.println("Error loading equipments: " + e.getMessage());
         }
     }
 
     private void saveDataToFile() {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
             for (Equipment eq : inventory.values()) {
                 String line = eq.getEquipmentID() + "," + eq.getName() + "," + eq.getStatus() + ","
                         + (eq.getPurchaseDate() != null ? sdf.format(eq.getPurchaseDate()) : "") + ","
                         + eq.getCondition();
                 bw.write(line);
                 bw.newLine();
             }
         } catch (IOException e) {
             System.out.println("Error saving equipments: " + e.getMessage());
         }
     }
 
     private void createSampleData() {
         System.out.println("Creating sample equipment data...");
         try {
             Date date = sdf.parse("2023-01-01");
             addEquipment(new Equipment(1, "Arduino Board", "Available", date, "Brand New"));
             addEquipment(new Equipment(2, "Phone", "Available", date, "Good"));
             addEquipment(new Equipment(3, "Robot", "Borrowed", date, "Needs Maintenance"));
             addEquipment(new Equipment(4, "3D Printer", "Available", date, "Good"));
             addEquipment(new Equipment(5, "VR Headset", "Borrowed", date, "Damaged"));
             addEquipment(new Equipment(6, "Oscilloscope", "Available", date, "Brand New"));
             addEquipment(new Equipment(7, "Laser Cutter", "Available", date, "Good"));
             addEquipment(new Equipment(8, "Projector", "Available", date, "Needs Maintenance"));
             addEquipment(new Equipment(9, "Microscope", "Borrowed", date, "Good"));
             addEquipment(new Equipment(10, "Tablet", "Available", date, "Out of Service"));
         } catch (Exception e) {
             System.out.println("Error creating sample data: " + e.getMessage());
         }
     }
 }
 