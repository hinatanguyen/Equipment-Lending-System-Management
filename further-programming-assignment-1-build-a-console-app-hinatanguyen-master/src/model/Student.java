/**
 * @author Nguyen Huy Hoang - s4041847
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Student extends Person {
    private String studentID;
    private List<LendingRecord> lendingRecords;

    public Student(int personID, String fullName, Date dateofBirth, String contactInfo, String studentID) {
        super(personID, fullName, dateofBirth, contactInfo);
        this.studentID = studentID;
        this.lendingRecords = new ArrayList<>();
    }

    //Parameterless constructor
    public Student() {
        super(0, "", null, "");
        this.studentID = "";
        this.lendingRecords = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public List<LendingRecord> getLendingRecords() {
        return lendingRecords;
    }

    public void addLendingRecord(LendingRecord record) {
        lendingRecords.add(record);
    }

    @Override
    public String toString() {
        return "Student [" + super.toString() + ", studentID: " + studentID + "]";
    }
}
