/**
 * @author Nguyen Huy Hoang - s4041847
 */

 package admin;

 import model.*;
 import service.*;
 import java.util.*;
 import java.text.SimpleDateFormat;
 import java.text.ParseException;
 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 
 public class AdminConsole {
     private static Scanner scanner = new Scanner(System.in);
     private static InventoryService inventoryService = new InventoryService();
     private static LendingService lendingService;
     private static List<Student> students = new ArrayList<>();
     private static List<Staff> staffs = new ArrayList<>();
     private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
     public static List<Student> getStudents() { // Add public getter
         return students;
     }
 
     public static List<Staff> getStaffs() { // Add public getter
         return staffs;
     }
 
     public static void main(String[] args) {
         loadSampleUserData();
         lendingService = new LendingService();
         while (true) {
             printMainMenu();
             int choice = getIntegerInput("Enter your choice: ");
             switch (choice) {
                 case 1:
                     viewAllEquipments();
                     break;
                 case 2:
                     viewAllLendingRecords();
                     break;
                 case 3:
                     viewAllStudents();
                     break;
                 case 4:
                     viewAllStaff();
                     break;
                 case 5:
                     manageEquipments();
                     break;
                 case 6:
                     manageLendingRecords();
                     break;
                 case 7:
                     generateReports();
                     break;
                 case 8:
                     System.out.println("Exiting the system. Goodbye!");
                     System.exit(0);
                     break;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
         }
     }
 
     private static void printMainMenu() {
         System.out.println("\n--- Admin Console ---");
         System.out.println("1. View all equipments");
         System.out.println("2. View all lending records");
         System.out.println("3. View all students");
         System.out.println("4. View all staff");
         System.out.println("5. Manage equipments");
         System.out.println("6. Manage lending records");
         System.out.println("7. Generate reports");
         System.out.println("8. Exit");
     }
 
     private static int getIntegerInput(String prompt) {
         System.out.print(prompt);
         while (!scanner.hasNextInt()) {
             System.out.print("Invalid input. Please enter a number: ");
             scanner.next();
         }
         return scanner.nextInt();
     }
 
     // --- View Methods ---
     private static void viewAllEquipments() {
         System.out.println("\n--- All Equipments ---");
         HashMap<Integer, Equipment> equipments = inventoryService.retrieveAllEquipments();
         if (equipments.isEmpty()) {
             System.out.println("No equipment found.");
         } else {
             for (Equipment eq : equipments.values()) {
                 System.out.println(eq);
             }
         }
     }
 
     private static void viewAllLendingRecords() {
         System.out.println("\n--- All Lending Records ---");
         List<LendingRecord> records = lendingService.retrieveAllLendingRecords();
         if (records.isEmpty()) {
             System.out.println("No lending records found.");
         } else {
             for (LendingRecord record : records) {
                 System.out.println(record);
             }
         }
     }
 
     private static void viewAllStudents() {
         System.out.println("\n--- All Students ---");
         if (students.isEmpty()) {
             System.out.println("No students found.");
         } else {
             for (Student student : students) {
                 System.out.println(student);
             }
         }
     }
 
     private static void viewAllStaff() {
         System.out.println("\n--- All Staff ---");
         if (staffs.isEmpty()) {
             System.out.println("No staff found.");
         } else {
             for (Staff staff : staffs) {
                 System.out.println(staff);
             }
         }
     }
 
     private static void manageEquipments() {
         while (true) {
             System.out.println("\n--- Manage Equipments ---");
             System.out.println("1. Add Equipment");
             System.out.println("2. Update Equipment");
             System.out.println("3. Remove Equipment");
             System.out.println("4. Back to Main Menu");
             int choice = getIntegerInput("Enter your choice: ");
             switch (choice) {
                 case 1:
                     addEquipment();
                     break;
                 case 2:
                     updateEquipment();
                     break;
                 case 3:
                     removeEquipment();
                     break;
                 case 4:
                     System.out.println("Returning to the main menu...");
                     return;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
         }
     }
 
 
     private static void addEquipment() {
         System.out.println("\n--- Add Equipment ---");
         System.out.print("Enter equipment ID: ");
         int id = getIntegerInput("");
         System.out.print("Enter equipment name: ");
         String name = scanner.next();
         System.out.print("Enter equipment status (Available/Borrowed): ");
         String status = scanner.next();
         System.out.print("Enter purchase date (yyyy-MM-dd): ");
         String dateStr = scanner.next();
         System.out.print("Enter equipment condition: ");
         String condition = scanner.next();
         try {
             Date purchaseDate = sdf.parse(dateStr);
             Equipment equipment = new Equipment(id, name, status, purchaseDate, condition);
             inventoryService.addEquipment(equipment);
             System.out.println("Equipment added successfully!");
         } catch (ParseException e) {
             System.out.println("Invalid date format.");
         }
     }
 
     private static void updateEquipment() {
         System.out.println("\n--- Update Equipment ---");
         System.out.print("Enter equipment ID to update: ");
         int id = getIntegerInput("");
         Equipment eq = inventoryService.retrieveAllEquipments().get(id);
         if (eq != null) {
             System.out.print("Enter new status: ");
             String status = scanner.next();
             System.out.print("Enter new condition: ");
             String condition = scanner.next();
             eq.setStatus(status);
             eq.setCondition(condition);
             inventoryService.updateEquipment(id, eq);
             System.out.println("Equipment updated successfully!");
         } else {
             System.out.println("Equipment not found.");
         }
     }
 
     private static void removeEquipment() {
         System.out.println("\n--- Remove Equipment ---");
         System.out.print("Enter equipment ID to remove: ");
         int id = getIntegerInput("");
         inventoryService.removeEquipment(id);
     }
 
     private static void manageLendingRecords() {
         while (true) {
             System.out.println("\n--- Manage Lending Records ---");
             System.out.println("1. Add Lending Record");
             System.out.println("2. Update Lending Record");
             System.out.println("3. Remove Lending Record");
             System.out.println("4. Back to Main Menu");
             int choice = getIntegerInput("Enter your choice: ");
             switch (choice) {
                 case 1:
                     addLendingRecord();
                     break;
                 case 2:
                     updateLendingRecord();
                     break;
                 case 3:
                     removeLendingRecord();
                     break;
                 case 4:
                     System.out.println("Returning to the main menu...");
                     return;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
         }
     }
 
     private static void addLendingRecord() {
         System.out.println("\n--- Add Lending Record ---");
         try {
             System.out.print("Enter Lending Record ID: ");
             int recordID = getIntegerInput("");
 
             System.out.print("Enter Borrower ID: ");
             int borrowerID = getIntegerInput("");
             Person borrower = lendingService.findBorrowerByID(borrowerID);
             if (borrower == null) {
                 System.out.println("Borrower not found.");
                 return;
             }
 
             System.out.print("Enter Equipment IDs (comma-separated): ");
             String equipmentIDsInput = scanner.next();
             String[] equipmentIDsArray = equipmentIDsInput.split(",");
             List<Equipment> equipments = new ArrayList<>();
 
             for (String equipmentID : equipmentIDsArray) {
                 Equipment equipment = inventoryService.retrieveAllEquipments().get(Integer.parseInt(equipmentID));
                 if (equipment != null && "Available".equalsIgnoreCase(equipment.getStatus())) {
                     equipments.add(equipment);
                 } else {
                     System.out.println("Equipment with ID " + equipmentID + " is not available.");
                     return;
                 }
             }
 
             System.out.print("Enter Supervising Academic Staff ID: ");
             int staffID = getIntegerInput("");
             Academic staff = lendingService.findAcademicByID(staffID);
             if (staff == null) {
                 System.out.println("Academic staff not found.");
                 return;
             }
 
             System.out.print("Enter Borrow Date (yyyy-MM-dd): ");
             Date borrowDate = sdf.parse(scanner.next());
 
             System.out.print("Enter Return Date (yyyy-MM-dd, leave blank if unknown): ");
             String returnDateInput = scanner.next();
             Date returnDate = returnDateInput.isEmpty() ? null : sdf.parse(returnDateInput);
 
             System.out.print("Enter Purpose: ");
             String purpose = scanner.next();
 
             for (Equipment equipment : equipments) {
                 equipment.setStatus("Borrowed");
                 inventoryService.updateEquipment(equipment.getEquipmentID(), equipment);
             }
 
             LendingRecord record = new LendingRecord(recordID, borrower, equipments, staff, borrowDate, returnDate, "Borrowed", purpose);
             lendingService.addLending(record);
             System.out.println("Lending record added successfully.");
         } catch (Exception e) {
             System.out.println("Error adding lending record: " + e.getMessage());
         }
     }
 
 
 
     private static void updateLendingRecord() {
         System.out.println("\n--- Update Lending Record ---");
         System.out.print("Enter Lending Record ID to update: ");
         int recordID = getIntegerInput("");
         LendingRecord existingRecord = lendingService.retrieveAllLendingRecords()
                 .stream()
                 .filter(record -> record.getRecordID() == recordID)
                 .findFirst()
                 .orElse(null);
         if (existingRecord == null) {
             System.out.println("Lending record not found.");
             return;
         }
 
         System.out.print("Enter new Status (e.g., Returned, Overdue): ");
         String newStatus = scanner.next();
         Date returnDate = null;
 
         if ("Returned".equalsIgnoreCase(newStatus)) {
             System.out.print("Enter Return Date (yyyy-MM-dd): ");
             try {
                 returnDate = sdf.parse(scanner.next());
             } catch (ParseException e) {
                 System.out.println("Invalid date format.");
                 return;
             }
             for (Equipment equipment : existingRecord.getEquipments()) {
                 equipment.setStatus("Available");
                 inventoryService.updateEquipment(equipment.getEquipmentID(), equipment);
             }
         }
 
         existingRecord.setStatus(newStatus);
         existingRecord.setReturnDate(returnDate);
         lendingService.updateLendingRecord(recordID, existingRecord);
         System.out.println("Lending record updated successfully.");
     }
 
     private static void removeLendingRecord() {
         System.out.println("\n--- Remove Lending Record ---");
         System.out.print("Enter Lending Record ID to remove: ");
         int recordID = getIntegerInput("");
 
         LendingRecord existingRecord = lendingService.retrieveAllLendingRecords()
                 .stream()
                 .filter(record -> record.getRecordID() == recordID)
                 .findFirst()
                 .orElse(null);
 
         if (existingRecord == null) {
             System.out.println("Lending record not found.");
             return;
         }
 
         if (!"Returned".equalsIgnoreCase(existingRecord.getStatus())) {
             System.out.println("Only returned records can be removed.");
             return;
         }
 
         lendingService.deleteLendingRecord(recordID);
         System.out.println("Lending record removed successfully.");
     }
 
     private static void generateReports() {
         System.out.println("\n--- Generate Reports ---");
         System.out.println("1. View and Export All Lending Records");
         System.out.println("2. View and Export Overdue Lending Records");
         System.out.println("3. View and Export Available Equipments");
         int choice = getIntegerInput("Enter your choice: ");
         try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
             switch (choice) {
                 case 1:
                     writer.write("--- All Lending Records ---\n");
                     lendingService.retrieveAllLendingRecords().forEach(record -> {
                         try {
                             writer.write(record.toString() + "\n");
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     });
                     break;
                 case 2:
                     writer.write("--- Overdue Lending Records ---\n");
                     lendingService.retrieveOverdueLendingRecords().forEach(record -> {
                         try {
                             writer.write(record.toString() + "\n");
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     });
                     break;
                 case 3:
                     writer.write("--- Available Equipments ---\n");
                     inventoryService.retrieveAvailableEquipments().forEach(equipment -> {
                         try {
                             writer.write(equipment.toString() + "\n");
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     });
                     break;
                 default:
                     System.out.println("Invalid choice.");
             }
             System.out.println("Report successfully exported to 'report.txt'.");
         } catch (IOException e) {
             System.out.println("Error exporting report: " + e.getMessage());
         }
     }
 
     private static void loadSampleUserData() {
         System.out.println("\nLoading sample students and staff...");
         try {
 
         // Student
             students.add(new Student(1, "Takahashi Yuki", sdf.parse("1998-05-12"), "yuki.t@example.com", "ST001"));
             students.add(new Student(2, "Suzuki Haruto", sdf.parse("1999-09-23"), "haruto.s@example.com", "ST002"));
             students.add(new Student(3, "Nakamura Aoi", sdf.parse("2000-03-17"), "aoi.n@example.com", "ST003"));
             students.add(new Student(4, "Watanabe Rin", sdf.parse("1997-11-05"), "rin.w@example.com", "ST004"));
             students.add(new Student(5, "Tanaka Kaito", sdf.parse("1999-01-30"), "kaito.t@example.com", "ST005"));
             students.add(new Student(6, "Yamamoto Hina", sdf.parse("2001-07-14"), "hina.y@example.com", "ST006"));
             students.add(new Student(7, "Sato Riku", sdf.parse("1998-12-09"), "riku.s@example.com", "ST007"));
             students.add(new Student(8, "Kobayashi Miyu", sdf.parse("2000-08-21"), "miyu.k@example.com", "ST008"));
             students.add(new Student(9, "Ito Sota", sdf.parse("1999-04-03"), "sota.i@example.com", "ST009"));
             students.add(new Student(10, "Kato Akari", sdf.parse("2001-02-18"), "akari.k@example.com", "ST010"));
         // Staff
             staffs.add(new Academic(11, "Dr. Yamada Hiroshi", sdf.parse("1975-06-22"), "hiroshi.y@example.com", "AC001", "Computer Science"));
             staffs.add(new Academic(12, "Dr. Kimura Satoshi", sdf.parse("1972-09-14"), "satoshi.k@example.com", "AC002", "Computer Science"));
             staffs.add(new Academic(13, "Dr. Nakajima Yumiko", sdf.parse("1980-03-07"), "yumiko.n@example.com", "AC003", "Computer Science"));
             staffs.add(new Academic(14, "Dr. Inoue Takashi", sdf.parse("1968-11-30"), "takashi.i@example.com", "AC004", "Computer Science"));
             staffs.add(new Academic(15, "Dr. Abe Mizuki", sdf.parse("1977-04-19"), "mizuki.a@example.com", "AC005", "Computer Science"));
             staffs.add(new Professional(16, "Ms. Sasaki Emi", sdf.parse("1982-08-25"), "emi.s@example.com", "PF001", "Computer Science"));
             staffs.add(new Professional(17, "Mr. Fujita Kenji", sdf.parse("1979-12-11"), "kenji.f@example.com", "PF002", "Computer Science"));
             staffs.add(new Professional(18, "Ms. Saito Ayumi", sdf.parse("1985-02-03"), "ayumi.s@example.com", "PF003", "Computer Science"));
             staffs.add(new Professional(19, "Mr. Hashimoto Daiki", sdf.parse("1976-07-09"), "daiki.h@example.com", "PF004", "Computer Science"));
             staffs.add(new Professional(20, "Ms. Mori Naomi", sdf.parse("1983-05-27"), "naomi.m@example.com", "PF005", "Computer Science"));
         } catch (ParseException e) {
             System.out.println("Error loading sample data.");
         }
     }
 }
 