package sample.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer  {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private int divID;
    private String customerpostal;
    private String division;
    private int countryId;
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();

    public Customer(int customerID, String customerName, String customerAddress,String customerPostalCode, String customerPhone, int divID, int countryId){
        this.customerID =customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.divID=divID;
        this.customerpostal=customerPostalCode;
        this.countryId=countryId;

    }

    public static ObservableList<Customer> getCustomers() {
        return customers;
    }
    public int getCustomerID() {
        return customerID;
    }
    public int getDivID() {
        return divID;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }
    public String getCustomerpostal() {
        return customerpostal;
    }
    public int getCountryId() {
        return countryId;
    }
    public String getDivision() {
        return division;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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
    public void setCustomerpostal(String customerpostal) {
        this.customerpostal = customerpostal;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public static void setCustomers(ObservableList<Customer> customers) {
        Customer.customers = customers;
    }
}
