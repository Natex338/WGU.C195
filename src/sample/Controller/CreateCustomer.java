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
import javafx.stage.Stage;
import sample.Model.Countries;
import sample.Model.Customer;
import sample.Utils.DBCountries;
import sample.Utils.DBCustomer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Utils.DBCountries.getAllRegionsByCountry;

/**
 * Create customer controller and controler fields
 */
public class CreateCustomer implements Initializable {
    @FXML
    private ComboBox<Countries> countryCombo;
    @FXML
    private ChoiceBox<String> regionCombo;
    @FXML
    private TextField namefield;
    @FXML
    private TextField addressfield;
    @FXML
    private TextField phonefield;
    @FXML
    private TextField postalfield;
    @FXML
    private ObservableList<Countries> allCountries= FXCollections.observableArrayList();

    /**
     * sets the country selection list
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
                allCountries.addAll(DBCountries.getAllCountries());
                countryCombo.setItems(allCountries);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }


    /**
     * @param actionEvent when clicking cancel it prompts to confirm you want to exit without saving.
     * @throws IOException throws error if it can find the view customer file.
     */
    public void onCancelApt(ActionEvent actionEvent) throws IOException {

        Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Unsaved Customer");
        exitAlert.setHeaderText("Confirm Exit");
        exitAlert.setContentText("Are you sure you want to close the the Customer without saving?");
        Optional<ButtonType> result = exitAlert.showAndWait();
        if(result.get()==ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/viewCustomers.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Customers");
            window.show();
        }
    }

    /**
     * @param actionEvent clicking save it gathers data and check for valid info, if its valid saves customer to the database.
     * @throws IOException throws error if it can't find the view customer FXML.
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        String name = namefield.getText();
        String address = addressfield.getText();
        String phone = phonefield.getText();
        String postal = postalfield.getText();
        int country=-1;
        int divisionID= -1;

        /**
         * Customer validity check
         */
        if(Customer.validCustomer(name,address,phone,postal) ){
            if ((countryCombo.getSelectionModel().getSelectedIndex())!=-1){
                country = countryCombo.getSelectionModel().getSelectedItem().getCountry_Id();
            }
            if (!regionCombo.getValue().isEmpty()) {
                divisionID = Countries.getDivision(regionCombo.getValue());
            }
            Customer newCustomer = new Customer(name,address,postal,phone,divisionID ,country);
            DBCustomer.insertCustomer(newCustomer);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/viewCustomers.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Customers");
            window.show();
        }
        else {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing Customer Info");
            alert.setTitle("Required customer info is missing");
            alert.setContentText("Please Enter all required customer info before saving");
            alert.showAndWait();
        }

    }

    /**
     * @param actionEvent once the country is selected the fist division data field is set with specific to the country.
     */
    public void onCountrySelect(ActionEvent actionEvent) {
        Countries bp = (Countries) countryCombo.getSelectionModel().getSelectedItem();
        regionCombo.setItems(getAllRegionsByCountry( bp.getCountry_Id()));

    }
}
