/**
 * @author Nguyen Huy Hoang - s4041847
 */

package model;

import java.util.Date;

public class Professional extends Staff {
    private String department;

    public Professional(int personID, String fullName, Date dateOfBirth, String contactInfo, String staffID, String department) {
        super(personID, fullName, dateOfBirth, contactInfo, staffID);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Professional [" + super.toString() + ", Department: " + department + "]";
    }
}
