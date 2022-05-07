package sample.Model;

import java.time.LocalDateTime;
import static sample.Utils.DBAppointments.dateTimeFormat;

/**
 * Appointment class fields
 */
public class Appointment {
    private int aptID;
    private String aptTitle;
    private String aptDesc;
    private String aptLocation;
    private String aptType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int contactID;
    private String contactName;
    private int customerID;
    private int userID;

    private  String customerName;


    /**
     * @param id Appointment ID
     * @param title Appointment Title
     * @param desc Appointment Description
     * @param location Appointment Location
     * @param type Appointment type
     * @param sDate Stat Date and time of Appointment
     * @param eDate End Date and time of Appointment
     * @param contactID Contact ID for Appointment
     * @param customerID Customer ID for the Appointment
     * @param userID User ID for the Appointment.
     * @param contactName Contact Name for the Appointment
     */
    public Appointment(int id, String title, String desc, String location, String type, LocalDateTime sDate, LocalDateTime eDate, int contactID, int customerID, int userID,String contactName){
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
       this.contactName=contactName;
   }
    public Appointment(int id, String title, String desc, String location, String type, LocalDateTime sDate, LocalDateTime eDate, int contactID, int customerID, int userID,String contactName, String customerName){
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
        this.contactName=contactName;
        this.customerName=customerName;
    }


    /**
     * @param title Appointment Title
     * @param desc Appointment Description
     * @param location Appointment Location
     * @param type Appointment type
     * @param sDate Stat Date and time of Appointment
     * @param eDate End Date and time of Appointment
     * @param contactID Contact ID for Appointment
     * @param customerID Customer ID for the Appointment
     * @param userID User ID for the Appointment.
     * @param contactName Contact Name for the Appointment
     */
    public Appointment(String title, String desc, String location, String type, LocalDateTime sDate, LocalDateTime eDate, int contactID, int customerID, int userID,String contactName){
        this.aptTitle =title;
        this.aptDesc =desc;
        this.aptLocation =location;
        this.aptType =type;
        this.startDate=sDate;
        this.endDate=eDate;
        this.contactID=contactID;
        this.customerID=customerID;
        this.userID=userID;
        this.contactName = contactName;
    }

    public Appointment(String title, String desc, String location, String type, LocalDateTime sDate, LocalDateTime eDate, int contactID, int customerID, int userID,String contactName, String customerName){
        this.aptTitle =title;
        this.aptDesc =desc;
        this.aptLocation =location;
        this.aptType =type;
        this.startDate=sDate;
        this.endDate=eDate;
        this.contactID=contactID;
        this.customerID=customerID;
        this.userID=userID;
        this.contactName = contactName;
        this.customerName = customerName;
    }

    /**
     * @return gets contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @return gets customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return gets contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @return Gets userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return Gets Appointment ID
     */
    public int getAptID() {
        return aptID;
    }

    /**
     * @return gets Appointment Description.
     */
    public String getAptDesc() {
        return aptDesc;
    }

    /**
     * @return gets Appointment Location.
     */
    public String getAptLocation() {
        return aptLocation;
    }

    /**
     * @return Gets Appointment Title
     */
    public String getAptTitle() {
        return aptTitle;
    }

    /**
     * @return Gets appointment Type
     */
    public String getAptType() {
        return aptType;
    }

    /**
     * @return Gets End Date and time of appointment.formatted.
     */
    public String getEndDate() {
       return dateTimeFormat(endDate);
    }

    /**
     * @return Gets start Date and time of appointment. formatted.
     */
    public String getStartDate() {
       return dateTimeFormat(startDate);
    }

    /**
     * @return gets start date
     */
    public LocalDateTime getStartDateTime(){
       return startDate;
    }

    /**
     * @return gets and date and time.
     */
    public LocalDateTime getEndDateTime(){
        return endDate;
    }

    /**
     * @param aptDesc sets appointment description.
     */
    public void setAptDesc(String aptDesc) {
        this.aptDesc = aptDesc;
    }

    /**
     * @param aptID sets appointment ID
     */
    public void setAptID(int aptID) {
        this.aptID = aptID;
    }

    /**
     * @param aptLocation sets appointment location.
     */
    public void setAptLocation(String aptLocation) {
        this.aptLocation = aptLocation;
    }

    /**
     * @param aptTitle sets appointment title
     */
    public void setAptTitle(String aptTitle) {
        this.aptTitle = aptTitle;
    }

    /**
     * @param aptType Sets appointment type
     */
    public void setAptType(String aptType) {
        this.aptType = aptType;
    }

    /**
     * @param endDate sets End date and time.
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * @param startDate sets start date
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @param customerID sets customer ID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @param contactID Sets Contact ID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @param userID Sets User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @param contactName Sets Contact ID
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    /**
     * @param aptTitle Appointment Title check
     * @param aptDesc Appointment description check
     * @param aptLocation Appointment Location check
     * @param aptType Appointment type check
     * @param start appointment start date check
     * @param end appointment end date check.
     * @return returns error message if any are wrong.
     */
    public static String isValidApt(String aptTitle, String aptDesc, String aptLocation, String aptType, LocalDateTime start, LocalDateTime end ){
        String errorMessage="";

        if (aptTitle.isEmpty()| aptDesc.isEmpty()| aptLocation.isEmpty()| aptType.isEmpty()){
            errorMessage = errorMessage + "Please fill out all required text fields \n";
        }
        else if (end.isBefore(start)){
            errorMessage= errorMessage + "End Date/Time cannot be before start Time\n";
        }
        else if (start.equals(end)){
            errorMessage = errorMessage +"Start Date/Time cannot be the same\n";
        }
        return errorMessage;
    }

    /**
     * @return Overiding the to string method to print the appointment location.
     */
    public String toString (){
        return aptLocation;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
