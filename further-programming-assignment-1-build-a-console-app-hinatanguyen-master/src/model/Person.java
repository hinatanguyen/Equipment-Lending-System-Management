/**
 * @author Nguyen Huy Hoang - s4041847
 */

package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Person {
    protected int personID;
    protected String fullName;
    protected Date dateOfBirth;
    protected String contactInfo;

    public Person(int personID, String fullName, Date dateOfBirth, String contactInfo) {
        this.personID = personID;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactInfo = contactInfo;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "PersonID: " + personID + ", Name: " + fullName + ", DateOfBirth: " + sdf.format(dateOfBirth) + ", ContactInfo: " + contactInfo;
    }
}
