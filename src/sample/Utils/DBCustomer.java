package sample.Utils;
import sample.Controller.LoginScreen;
import sample.Model.Customer;
import sample.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class DBCustomer {


    public static void insertCustomer(Customer customer) {
        String customerName =customer.getCustomerName();
        String address = customer.getCustomerAddress();
        String postal= customer.getCustomerpostal();
        String phone = customer.getCustomerPhone();
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        String createdBY= LoginScreen.loggedInUser.getUserName();
        Timestamp LastUpdated =new Timestamp(System.currentTimeMillis());
        int ID = customer.getDivID();

        try {
            String insertCustomer = "Insert into Customers \n" +
                    "(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update,Divsion_ID)\n" +
                    "VALUES(customerName,address,postal,phone,createdDate,createdBY,LastUpdated,ID);";


            PreparedStatement statement = (PreparedStatement) DBQuery.getStatement().executeQuery(insertCustomer);

            ResultSet result = statement.executeQuery(insertCustomer);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



