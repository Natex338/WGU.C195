package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import sample.*;
import sample.Model.User;
import sample.Utils.DBQuery;


public class ViewCustomers implements Initializable {

    @FXML
    private Button addCustButton;
    @FXML
    private Button modifyCustButton;
    @FXML
    private Button deleteCustButton;
    @FXML
    private TableView <Customer>customerListView;
    @FXML
    private TableColumn <Customer,Integer> custIDCol;
    @FXML
    private TableColumn <Customer, String> custNameCol;
    @FXML
    private TableColumn <Customer, String> custAddrCol;
    @FXML
    private TableColumn <Customer, String> custPhoneCol;
    @FXML
    private TableColumn <Customer,Integer> custDivIDCol;


    private ObservableList<Customer>allCustomers = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle){

        PropertyValueFactory<Customer, String> customerName = new PropertyValueFactory<>("CustomerName");
        custNameCol.setCellValueFactory(customerName);
        PropertyValueFactory<Customer, String> customerPhone = new PropertyValueFactory<>("CustomerPhone");
        custPhoneCol.setCellValueFactory(customerPhone);
        PropertyValueFactory<Customer, Integer> customerID = new PropertyValueFactory<>("CustomerID");
        custIDCol.setCellValueFactory(customerID);
        PropertyValueFactory<Customer, String> customerAddress = new PropertyValueFactory<>("customerAddress");
        custAddrCol.setCellValueFactory(customerAddress);
        PropertyValueFactory<Customer, Integer> divID = new PropertyValueFactory<>("divID");
        custDivIDCol.setCellValueFactory(divID);
        customerListView.setItems(allCustomers);
        try {

            getAllCustomers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


    public void onAddCust(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/CreateCustomer.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Customers");
        window.show();
    }

    public void onModCust(ActionEvent actionEvent) {

    }

    public void onDeleteCust(ActionEvent actionEvent) {
    }

    public void onHomePage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/homePage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HomePage");
        window.show();
    }

        public void getAllCustomers() throws SQLException {

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
                String fulladdress = address + ", " + region;
                Customer c = new Customer(customerID, name, fulladdress, phone, divID, postalCode);
                allCustomers.add(c);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Done with list");
    }

}
