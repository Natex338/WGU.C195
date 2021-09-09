package sample.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controller.LoginScreen;
import sample.Model.Appointment;
import sample.Model.Customer;
import java.sql.*;

public abstract class DBCustomer {


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

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            Statement statement = DBQuery.getStatement();

            String getAllCustQuery = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, phone, customers.Division_ID, Division, first_level_divisions.COUNTRY_ID, Country \n" +
                    "FROM customers,first_level_divisions,countries \n" +
                    "WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.COUNTRY_ID = countries.Country_ID;";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                String address = result.getString("Address");
                String name = result.getString("Customer_Name");
                int customerID = Integer.parseInt(result.getString("Customer_ID"));
                String postalCode = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                int divID = Integer.parseInt(result.getString("Division_ID"));
                String region = result.getString("Division");
                int countryid= result.getInt("COUNTRY_ID");
                String countryName = result.getString("Country");
                Customer c = new Customer(customerID, name, address, postalCode, region, phone, divID, countryid, countryName);
                allCustomers.add(c);
            }
            Customer.setCustomers(allCustomers);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allCustomers;
    }

    public static void deleteCustomer(Customer customer) throws SQLException {

        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?;";
        PreparedStatement ps = DBConnection.startConnection().prepareStatement(deleteStatement);
        ps.setInt(1,customer.getCustomerID());
        ps.execute();
    }

}




