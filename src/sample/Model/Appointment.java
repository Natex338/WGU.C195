package sample.Model;

import java.time.LocalDateTime;
import static sample.Utils.DBAppointments.dateTimeFormat;

public class Appointment {
    private int aptID;
    private String aptTitle;
    private String aptDesc;
    private String aptLocation;
    private String aptType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int contactID;
    private int customerID;
    private int userID;

    public Appointment(int id, String title, String desc, String location, String type, LocalDateTime sDate, LocalDateTime eDate, int contactID, int customerID, int userID){
       this.aptID =id;
       this.aptTitle =title;
       this.aptDesc =desc;
       this.aptLocation =location;
       this.aptType =type;
       this.startDate=sDate;
       this.endDate=eDate;
       this.contactID=contactID;
       this.customerID=customerID;
       this.userID=userID;
   }
    public Appointment(String title, String desc, String location, String type, LocalDateTime sDate, LocalDateTime eDate, int contactID, int customerID, int userID){
        this.aptTitle =title;
        this.aptDesc =desc;
        this.aptLocation =location;
        this.aptType =type;
        this.startDate=sDate;
        this.endDate=eDate;
        this.contactID=contactID;
        this.customerID=customerID;
        this.userID=userID;
    }

    public int getCustomerID() {
        return customerID;
    }
    public int getContactID() {
        return contactID;
    }
    public int getUserID() {
        return userID;
    }
    public int getAptID() {
        return aptID;
    }
    public String getAptDesc() {
        return aptDesc;
    }
    public String getAptLocation() {
        return aptLocation;
    }
    public String getAptTitle() {
        return aptTitle;
    }
    public String getAptType() {
        return aptType;
    }
    public String getEndDate() {
       return dateTimeFormat(endDate);
    }
    public String getStartDate() {
       return dateTimeFormat(startDate);
    }
    public LocalDateTime getStartDateTime(){
       return startDate;
    }
    public LocalDateTime getEndDateTime(){
        return endDate;
    }


    public void setAptDesc(String aptDesc) {
        this.aptDesc = aptDesc;
    }
    public void setAptID(int aptID) {
        this.aptID = aptID;
    }
    public void setAptLocation(String aptLocation) {
        this.aptLocation = aptLocation;
    }
    public void setAptTitle(String aptTitle) {
        this.aptTitle = aptTitle;
    }
    public void setAptType(String aptType) {
        this.aptType = aptType;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
