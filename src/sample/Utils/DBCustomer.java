package sample.Utils;
import sample.Controller.LoginScreen;
import sample.Model.Customer;
import java.sql.*;

public class DBCustomer {


    public static void insertCustomer(Customer customer) {
        String customerName =customer.getCustomerName();
        String address = customer.getAddress();
        String postal= customer.getCustomerPostal();
        String phone = customer.getCustomerPhone();
        String createdBY= LoginScreen.loggedInUser.getUserName();
        int ID = customer.getDivID();

        try {
            String insertCustomer = "Insert into customers (Customer_ID,Customer_Name, Address, Postal_Code, Phone, Created_By,Division_ID,Last_Updated_By) VALUES(null,?,?,?,?,?,?,?);";
            PreparedStatement ps = DBConnection.startConnection().prepareStatement(insertCustomer);
            ps.setString(1,customerName);
            ps.setString(2,address);
            ps.setString(3,postal);
            ps.setString(4,phone);
            ps.setString(5,createdBY);
            ps.setInt(6,ID);
            ps.setString(7,createdBY);
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCustomer(Customer customer){
        String customerName =customer.getCustomerName();
        String address = customer.getAddress();
        String postal= customer.getCustomerPostal();
        String phone = customer.getCustomerPhone();
        String createdBY= LoginScreen.loggedInUser.getUserName();
        int ID = customer.getDivID();
        int custID=customer.getCustomerID();

        try {
            String insertCustomer = "UPDATE customers SET Customer_Name =?, Address =?, Postal_Code=?, Phone =?, Created_By = ?, Division_ID = ?, Last_Updated_By = ? WHERE Customer_ID =? ;";
            PreparedStatement ps = DBConnection.startConnection().prepareStatement(insertCustomer);
            ps.setString(1,customerName);
            ps.setString(2,address);
            ps.setString(3,postal);
            ps.setString(4,phone);
            ps.setString(5,createdBY);
            ps.setInt(6,ID);
            ps.setString(7,createdBY);
            ps.setInt(8, custID);
            System.out.println(custID);
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}




