package sample.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Utils.DBCustomer;
import java.sql.SQLException;


/**
 * Customer Class
 */
public class Client {
    private int customerID;
    private String clientName;
    private String address;
    private String clientPhone;
    private int divID;
    private String customerPostal;
    private String division;
    private int countryId;
    private String country;
    private static ObservableList<Client> clients = FXCollections.observableArrayList();
    private String addressRegion;

    /**
     * @param customerID  Customer ID
     * @param clientName Customer Name
     * @param address Customer Address
     * @param customerPostalCode Customer Postal Code
     * @param region Customer Region
     * @param clientPhone Customer Phone
     * @param divID Customer Division ID
     * @param countryId Customer Country ID
     * @param countryName Customer Country Name
     */
    public Client(int customerID, String clientName, String address, String customerPostalCode, String region, String clientPhone, int divID, int countryId, String countryName){
        this.customerID =customerID;
        this.clientName = clientName;
        this.address = address;
        this.clientPhone = clientPhone;
        this.divID=divID;
        this.customerPostal =customerPostalCode;
        this.countryId=countryId;
        this.country =countryName;
        this.division=region;
        this.addressRegion = address +", "+ region;
    }

    /**
     * @param clientName  Customer Name
     * @param address Customer Address
     * @param customerPostalCode Customer Postal Code
     *
     * @param clientPhone Customer Phone
     * @param divID Customer Division ID
     * @param countryId Customer Country ID
     */
    public Client(String clientName, String address, String customerPostalCode, String clientPhone, int divID, int countryId){
        this.clientName = clientName;
        this.address = address;
        this.clientPhone = clientPhone;
        this.divID=divID;
        this.customerPostal =customerPostalCode;
        this.countryId=countryId;

    }

    /**
     * Customer Getters
     */
    public static ObservableList<Client> getCustomers() throws SQLException {
        return DBCustomer.getAllCustomers();
    }
    public String getAddress() {
        return address;
    }
    public String getAddressRegion() {
        return addressRegion;
    }
    public int getCustomerID() {
        return customerID;
    }
    public int getDivID() {
        return divID;
    }
    public String getCustomerAddress() {
        return addressRegion;
    }
    public String getCustomerName() {
        return clientName;
    }
    public String getClientPhone() {
        return clientPhone;
    }
    public String getCustomerPostal() {
        return customerPostal;
    }
    public int getCountryId() {
        return countryId;
    }
    public String getDivision() {
        return division;
    }
    public String getCountry() {
        return country;
    }



    /**
     * Customer Setters
     */

    public void setCustomerAddress(String customerAddress) {
        this.address = customerAddress;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setCustomerName(String customerName) {
        this.clientName = customerName;
    }
    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
    public void setDivID(int divID) {
        this.divID = divID;
    }
    public void setCustomerPostal(String customerPostal) {
        this.customerPostal = customerPostal;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }
    public static void setCustomers(ObservableList<Client> clients) {
        Client.clients = clients;
    }


    /**
     * @param name Customer Name Check
     * @param address Customer Address Check
     * @param phone Customer Phone Check
     * @param postal Customer Postal Check
     * @return Returns true if fields are not empty.
     */
    public static boolean validCustomer(String name, String address, String phone, String postal){
        if (name.isEmpty() | address.isEmpty() | phone.isEmpty()|postal.isEmpty()){
            return false;
        }
        else return true;
    }

    /**
     * @return Overriding the toString method to print Customer Name.
     */
    public String toString(){
        return clientName;
    }
}
