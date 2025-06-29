/**
 * @author Nguyen Huy Hoang - s4041847
 */

 package service;

 import admin.AdminConsole;
 import model.LendingRecord;
 import model.Equipment;
 import model.Person;
 import model.Academic;
 import model.Student;
 import model.Staff;
 import java.util.*;
 import java.io.*;
 import java.text.SimpleDateFormat;
 
 public class LendingService implements LendingManager {
     private List<LendingRecord> lendingRecords;
     private final String dataFile = "lendingRecords.txt";
     private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     private InventoryService inventoryService;
 
     public LendingService() {
         lendingRecords = new ArrayList<>();
         inventoryService = new InventoryService();
         loadDataFromFile();
     }
 
     @Override
     public void addLending(LendingRecord record) {
         lendingRecords.add(record);
         saveDataToFile();
     }
 
     @Override
     public void updateLendingRecord(int recordID, LendingRecord updatedRecord) {
         for (int i = 0; i < lendingRecords.size(); i++) {
             if (lendingRecords.get(i).getRecordID() == recordID) {
                 lendingRecords.set(i, updatedRecord);
                 saveDataToFile();
                 return;
             }
         }
         System.out.println("Record not found!");
     }
 
     @Override
     public void deleteLendingRecord(int recordID) {
         lendingRecords.removeIf(record -> record.getRecordID() == recordID);
         saveDataToFile();
     }
 
     @Override
     public List<LendingRecord> retrieveAllLendingRecords() {
         System.out.println("Retrieving all lending records. Total records: " + lendingRecords.size());
         return lendingRecords;
     }
 
     @Override
     public List<LendingRecord> retrieveLendingRecordsByBorrower(int borrowerID) {
         List<LendingRecord> result = new ArrayList<>();
         for (LendingRecord record : lendingRecords) {
             if (record.getBorrower() != null && record.getBorrower().getPersonID() == borrowerID) {
                 result.add(record);
             }
         }
         return result;
     }
 
     @Override
     public List<LendingRecord> retrieveLendingRecordsByEquipmentID(int equipmentID) {
         List<LendingRecord> result = new ArrayList<>();
         for (LendingRecord record : lendingRecords) {
             for (Equipment eq : record.getEquipments()) {
                 if (eq.getEquipmentID() == equipmentID) {
                     result.add(record);
                     break;
                 }
             }
         }
         return result;
     }
 
     @Override
     public List<LendingRecord> retrieveOverdueLendingRecords() {
         List<LendingRecord> result = new ArrayList<>();
         Date today = new Date();
         for (LendingRecord record : lendingRecords) {
             if (record.getReturnDate() != null && record.getReturnDate().before(today)
                     && !record.getStatus().equalsIgnoreCase("Returned")) {
                 result.add(record);
             }
         }
         return result;
     }
 
     private void loadDataFromFile() {
         File file = new File(dataFile);
         if (!file.exists()) {
             System.out.println("File not found: " + file.getAbsolutePath());
             return;
         }
 
         System.out.println("Loading lending records from file: " + file.getAbsolutePath());
 
         try (BufferedReader br = new BufferedReader(new FileReader(file))) {
             String line;
             while ((line = br.readLine()) != null) {
                 System.out.println("Reading line: " + line);
                 String[] parts = line.split(",");
                 if (parts.length < 8) {
                     System.out.println("Skipping malformed line: " + line);
                     continue;
                 }
 
                 try {
                     int recordID = Integer.parseInt(parts[0]);
                     int borrowerID = Integer.parseInt(parts[1]);
                     String[] equipmentIDs = parts[2].split("-");
                     int staffID = Integer.parseInt(parts[3]);
                     Date borrowDate = parts[4].isEmpty() ? null : sdf.parse(parts[4]);
                     Date returnDate = parts[5].isEmpty() ? null : sdf.parse(parts[5]);
                     String status = parts[6];
                     String purpose = parts[7];
 
                     Person borrower = findBorrowerByID(borrowerID);
                     Academic staff = findAcademicByID(staffID);
 
                     if (borrower == null) {
                         System.out.println("Borrower not found for ID: " + borrowerID);
                         continue;
                     }
 
                     if (staff == null) {
                         System.out.println("Staff not found for ID: " + staffID);
                         continue;
                     }
 
                     List<Equipment> equipments = new ArrayList<>();
                     for (String id : equipmentIDs) {
                         if (!id.isEmpty()) {
                             Equipment equipment = inventoryService.retrieveAllEquipments().get(Integer.parseInt(id));
                             if (equipment != null) {
                                 equipments.add(equipment);
                             } else {
                                 System.out.println("Equipment not found for ID: " + id);
                             }
                         }
                     }
 
                     LendingRecord record = new LendingRecord(recordID, borrower, equipments, staff, borrowDate, returnDate, status, purpose);
                     lendingRecords.add(record);
                     System.out.println("Lending Record Loaded: " + recordID);
 
                 } catch (Exception e) {
                     System.out.println("Error processing line: " + line + " - " + e.getMessage());
                 }
             }
         } catch (IOException e) {
             System.out.println("Error reading lending records file: " + e.getMessage());
         }
 
         System.out.println("Finished loading records. Total records loaded: " + lendingRecords.size());
     }
 
 
     private void saveDataToFile() {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, false))) { // Set false to overwrite
             for (LendingRecord record : lendingRecords) {
                 StringBuilder equipmentIDs = new StringBuilder();
                 for (Equipment equipment : record.getEquipments()) {
                     equipmentIDs.append(equipment.getEquipmentID()).append("-");
                 }
                 String borrowDateStr = record.getBorrowDate() != null ? sdf.format(record.getBorrowDate()) : "";
                 String returnDateStr = record.getReturnDate() != null ? sdf.format(record.getReturnDate()) : "";
                 int borrowerID = record.getBorrower() != null ? record.getBorrower().getPersonID() : 0;
                 int staffID = record.getStaff() != null ? record.getStaff().getPersonID() : 0;
                 String line = record.getRecordID() + "," + borrowerID + "," + equipmentIDs
                         + "," + staffID + "," + borrowDateStr + "," + returnDateStr + "," + record.getStatus() + "," + record.getPurpose();
                 System.out.println("Saving line to file: " + line);
                 bw.write(line);
                 bw.newLine();
             }
         } catch (IOException e) {
             System.out.println("Error saving lending records: " + e.getMessage());
         }
     }
 
     public Person findBorrowerByID(int borrowerID) {
         for (Student student : AdminConsole.getStudents()) {
             if (student.getPersonID() == borrowerID) {
                 return student;
             }
         }
         for (Staff staff : AdminConsole.getStaffs()) {
             if (staff.getPersonID() == borrowerID) {
                 return staff;
             }
         }
         return null;
     }
 
     public Academic findAcademicByID(int staffID) {
         for (Staff staff : AdminConsole.getStaffs()) {
             if (staff instanceof Academic && staff.getPersonID() == staffID) {
                 return (Academic) staff;
             }
         }
         return null;
     }
 }
 