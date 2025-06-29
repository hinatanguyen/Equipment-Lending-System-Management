/**
 * @author Nguyen Huy Hoang - s4041847
 */

package model;

import java.util.Date;

public class Staff extends Person {
    protected String staffID;

    public Staff(int personID, String fullName, Date dateOfBirth, String contactInfo, String staffID) {
        super(personID, fullName, dateOfBirth, contactInfo);
        this.staffID = staffID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return "Staff [" + super.toString() + ", StaffID: " + staffID + "]";
    }
}
