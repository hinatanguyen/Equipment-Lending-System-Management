/**
 * @author Nguyen Huy Hoang - s4041847
 */

package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Equipment {
    private int equipmentID;
    private String name;
    private String status; // Available or Borrowed
    private Date purchaseDate;
    private String condition; // Brand New, Good, Needs Maintenance, Damaged, or Out of Service

    public Equipment(int equipmentID, String name, String status, Date purchaseDate, String condition) {
        this.equipmentID = equipmentID;
        this.name = name;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.condition = condition;
    }

    public int getEquipmentID() { // Fixed method name
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) { // Fixed method name
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "EquipmentID: " + equipmentID + ", Name: " + name + ", Status: " + status +
                ", PurchaseDate: " + (purchaseDate != null ? sdf.format(purchaseDate) : "Unknown") +
                ", Condition: " + condition;
    }
}
