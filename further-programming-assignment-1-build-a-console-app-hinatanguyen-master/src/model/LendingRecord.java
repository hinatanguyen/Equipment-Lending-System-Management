/**
 * @author Nguyen Huy Hoang - s4041847
 */

 package model;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import java.text.SimpleDateFormat;
 
 public class LendingRecord {
     private int recordID;
     private Person borrower;
     private List<Equipment> equipment;
     private Academic staff;
     private Date borrowDate;
     private Date returnDate;
     private String status;
     private String purpose;
 
     public LendingRecord(int recordID, Person borrower, List<Equipment> equipment, Academic staff,
                          Date borrowDate, Date returnDate, String status, String purpose) {
         this.recordID = recordID;
         this.borrower = borrower;
         this.equipment = equipment != null ? equipment : new ArrayList<>();
         this.staff = staff;
         this.borrowDate = borrowDate != null ? borrowDate : new Date();
         this.returnDate = returnDate;
         this.status = status;
         this.purpose = purpose;
     }
 
     public int getRecordID() {
         return recordID;
     }
 
     public void setRecordID(int recordID) {
         this.recordID = recordID;
     }
 
     public Person getBorrower() {
         return borrower;
     }
 
     public void setBorrower(Person borrower) {
         this.borrower = borrower;
     }
 
     public List<Equipment> getEquipments() { // Renamed to plural
         return equipment;
     }
 
     public void setEquipments(List<Equipment> equipments) { // Renamed to plural
         this.equipment = equipments;
     }
 
     public Academic getStaff() {
         return staff;
     }
 
     public void setStaff(Academic staff) {
         this.staff = staff;
     }
 
     public Date getBorrowDate() {
         return borrowDate;
     }
 
     public void setBorrowDate(Date borrowDate) {
         this.borrowDate = borrowDate;
     }
 
     public Date getReturnDate() {
         return returnDate;
     }
 
     public void setReturnDate(Date returnDate) {
         this.returnDate = returnDate;
     }
 
     public String getStatus() {
         return status;
     }
 
     public void setStatus(String status) {
         this.status = status;
     }
 
     public String getPurpose() {
         return purpose;
     }
 
     public void setPurpose(String purpose) {
         this.purpose = purpose;
     }
 
     @Override
     public String toString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
         StringBuilder equipmentList = new StringBuilder();
         for (Equipment equipment : equipment) {
             equipmentList.append("  - ID: ").append(equipment.getEquipmentID()).append("\n");
         }
         String borrowerInfo = borrower != null ? borrower.toString() : "None";
         String staffInfo = staff != null ? staff.toString() : "None";
 
         return "Record ID: " + recordID + "\n" +
                 "Borrower: " + borrowerInfo + "\n" +
                 "Equipment:" + equipmentList.toString().trim() + "\n" +
                 "Staff: " + staffInfo + "\n" +
                 "Borrow Date: " + sdf.format(borrowDate) + "\n" +
                 "Return Date: " + (returnDate != null ? sdf.format(returnDate) : "N/A") + "\n" +
                 "Status: " + status + "\n" +
                 "Purpose: " + purpose + "\n";
     }
 }
 