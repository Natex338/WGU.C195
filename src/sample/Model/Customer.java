package sample.Model;


public class Customer  {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private int divID;
    private String customerpostal;
    private String division;
    private int countryId;

    public Customer(int customerID, String customerName, String customerAddress, String customerPhone, int divID,String customerPostalCode,String division, int countryId){
        this.customerID =customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.divID=divID;
        this.customerpostal=customerPostalCode;
        this.division=division;
        this.countryId=countryId;

    }
    public Customer(int customerID, String customerName, String customerAddress, String customerPhone, int divID,String customerPostalCode){
        this.customerID =customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.divID=divID;
        this.customerpostal=customerPostalCode;
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
}
