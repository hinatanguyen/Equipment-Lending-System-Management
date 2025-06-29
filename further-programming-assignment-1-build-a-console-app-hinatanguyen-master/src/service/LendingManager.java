/**
 * @author Nguyen Huy Hoang - s4041847
 */

 package service;

 import model.LendingRecord;
 import java.util.List;
 
 public interface LendingManager {
     void addLending(LendingRecord record);
     void updateLendingRecord(int recordID, LendingRecord updatedRecord);
     void deleteLendingRecord(int recordID);
     List<LendingRecord> retrieveAllLendingRecords();
     List<LendingRecord> retrieveLendingRecordsByBorrower(int borrowerID);
     List<LendingRecord> retrieveLendingRecordsByEquipmentID(int equipmentID);
     List<LendingRecord> retrieveOverdueLendingRecords();
 }
 