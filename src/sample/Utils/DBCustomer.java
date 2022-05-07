package sample.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controller.LoginScreen;
import sample.Model.Client;

import java.sql.*;

/**
 * DOA for Customers
 */
public abstract class DBCustomer {


    /**
     * @param client Customer being added to the Database
     */
    public static void insertCustomer(Client client) {
        String customerName = client.getCustomerName();
        String address = client.getAddress();
        String postal= client.getCustomerPostal();
        String phone = client.getClientPhone();
        String createdBY= LoginScreen.loggedInUser.getUserName();
        int ID = client.getDivID();

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

    /**
     * @param client Customer being updated in the database
     */
    public static void updateCustomer(Client client){
        String customerName = client.getCustomerName();
        String address = client.getAddress();
        String postal= client.getCustomerPostal();
        String phone = client.getClientPhone();
        String createdBY= LoginScreen.loggedInUser.getUserName();
        int ID = client.getDivID();
        int custID= client.getCustomerID();

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
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return returns all customer objects
     * @throws SQLException throws SQL error if it can't access the database.
     */

    public  static int MaxNum(){
        int maxID=-1;
       try {
           Statement statement = DBQuery.getStatement();

           String getAllCustQuery = "SELECT MAX(Customer_ID) as MAX_ID FROM customers;";
           ResultSet result = statement.executeQuery(getAllCustQuery);
           while (result.next()){
               maxID=result.getInt("MAX_ID");
           }
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }
        return maxID++;
    }

    public static ObservableList<Client> getAllCustomers() throws SQLException {
        ObservableList<Client> allClients = FXCollections.observableArrayList();
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
                Client c = new Client(customerID, name, address, postalCode, region, phone, divID, countryid, countryName);
                allClients.add(c);
            }
            Client.setCustomers(allClients);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allClients;
    }

    /**
     * @param client customer being deleted from the database
     * @throws SQLException throws SQL error if it can't access the database.
     */
    public static void deleteCustomer(Client client) throws SQLException {

        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?;";
        PreparedStatement ps = DBConnection.startConnection().prepareStatement(deleteStatement);
        ps.setInt(1, client.getCustomerID());
        ps.execute();
    }

}




