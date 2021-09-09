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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.Appointment;
import sample.Model.Countries;
import sample.Model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import sample.*;
import sample.Model.User;
import sample.Utils.DBAppointments;
import sample.Utils.DBCountries;
import sample.Utils.DBCustomer;
import sample.Utils.DBQuery;


public class ViewCustomers implements Initializable {

    @FXML
    private Button addCustButton;
    @FXML
    private Button modifyCustButton;
    @FXML
    private Button deleteCustButton;
    @FXML
    private TableView<Customer> customerListView;
    @FXML
    private TableColumn<Customer, Integer> custIDCol;
    @FXML
    private TableColumn<Customer, String> custNameCol;
    @FXML
    private TableColumn<Customer, String> custAddrCol;
    @FXML
    private TableColumn<Customer, String> custPhoneCol;
    @FXML
    private TableColumn<Customer, String> custCountryCol;


    private ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    public static Customer modCustomer;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        custIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        custAddrCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("addressRegion"));
        custCountryCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("country"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        try {
            customerListView.setItems(Customer.getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            allCountries.addAll(Countries.getCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void onAddCust(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/CreateCustomer.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Add Customers");
        window.show();
    }

    public void onModCust(ActionEvent actionEvent) throws IOException {
        if (customerListView.getSelectionModel().getSelectedIndex() != -1) {
            modCustomer = customerListView.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/ModifyCustomer.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Modify Customer");
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No customer Selected");
            alert.setContentText("No customer Selected");
            alert.showAndWait();
        }


    }

    public void onDeleteCust(ActionEvent actionEvent) throws SQLException {
        if (customerListView.getSelectionModel().getSelectedIndex() != -1) {
            Customer customer = customerListView.getSelectionModel().getSelectedItem();
            int customerID = customer.getCustomerID();
            if (customerApptDelete(customerID)){
                DBCustomer.deleteCustomer(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Customer: "+ customer.getCustomerName() +" has been Deleted");
                alert.setTitle("Customer Successfully Deleted");
                alert.showAndWait();
            }
        }
        customerListView.setItems(DBCustomer.getAllCustomers());
    }

    public void onHomePage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/homePage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HomePage");
        window.show();

    }

    public boolean customerApptDelete(int cID) throws SQLException {

        for (Appointment c : DBAppointments.getAllApt()) {
            if (c.getCustomerID() == cID) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Customer has Appointments");
                alert.setTitle("Cannot Delete Customer!");
                alert.setContentText("Do you want to delete all customer appointments?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    DBAppointments.deleteCustomerApt(cID);
                    return true;
                } else return false;
            }
        }
        return true;
    }
}
