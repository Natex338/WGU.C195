package sample.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer  {
    private int customerID;
    private String customerName;
    private String address;
    private String customerPhone;
    private int divID;
    private String customerPostal;
    private String division;
    private int countryId;
    private String country;
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();
    private String addressRegion;

    public Customer(int customerID, String customerName, String address,String customerPostalCode, String region, String customerPhone, int divID, int countryId,String countryName){
        this.customerID =customerID;
        this.customerName = customerName;
        this.address = address;
        this.customerPhone = customerPhone;
        this.divID=divID;
        this.customerPostal =customerPostalCode;
        this.countryId=countryId;
        this.country =countryName;
        this.division=region;
        this.addressRegion = address +", "+ region;
    }
    public Customer( String customerName, String address,String customerPostalCode, String customerPhone, int divID, int countryId){
        this.customerName = customerName;
        this.address = address;
        this.customerPhone = customerPhone;
        this.divID=divID;
        this.customerPostal =customerPostalCode;
        this.countryId=countryId;

    }

    public static ObservableList<Customer> getCustomers() {
        return customers;
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
        return customerName;
    }
    public String getCustomerPhone() {
        return customerPhone;
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

    public void setCustomerAddress(String customerAddress) {
        this.address = customerAddress;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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
    public static void setCustomers(ObservableList<Customer> customers) {
        Customer.customers = customers;
    }
    public static boolean validCustomer(String name, String address, String phone, String postal){
        if (name.isEmpty() | address.isEmpty() | phone.isEmpty()|postal.isEmpty()){
            return false;
        }
        else return true;
    }
}
