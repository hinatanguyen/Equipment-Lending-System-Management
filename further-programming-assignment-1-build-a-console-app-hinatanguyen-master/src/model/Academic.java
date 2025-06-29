/**
 * @author Nguyen Huy Hoang - s4041847
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Academic extends Staff {
    private String expertise;
    private List<Student> supervisedStudents;

    public Academic(int personID, String fullName, Date dateOfBirth, String contactInfo, String staffID, String expertise) {
        super(personID, fullName, dateOfBirth, contactInfo, staffID);
        this.expertise = expertise;
        this.supervisedStudents = new ArrayList<>();
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public List<Student> getSupervisedStudents() {
        return supervisedStudents;
    }

    public void addSupervisedStudents(Student student) {
        supervisedStudents.add(student);
    }

    @Override
    public String toString() {
        return "Academic [" + super.toString() + ", expertise=" + expertise + "]";
    }
}
