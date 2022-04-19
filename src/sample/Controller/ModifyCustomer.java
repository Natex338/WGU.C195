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
import sample.Model.Client;
import sample.Model.Countries;
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
 * Modify customer controller
 */
public class ModifyCustomer implements Initializable {
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
    private Label custIDfield;
    private ObservableList<Countries> allCountries= FXCollections.observableArrayList();
    private Client holdClient;


    /**
     * @param url sets the country fields
     * @param resourceBundle populates the customer data in fields
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/**
 * gets all the countries from the database and sets the country combo box with them.
 */
        try {
            allCountries.addAll(DBCountries.getAllCountries());
            countryCombo.setItems(allCountries);
        } catch(SQLException e){
            e.printStackTrace();
        }

/**
 * sets all the customer fields with the customer that is selected info.
 */
        holdClient = ViewCustomers.modClient;
        custIDfield.setText(String.valueOf(holdClient.getCustomerID()));
        namefield.setText(holdClient.getCustomerName());
        addressfield.setText(holdClient.getAddress());
        phonefield.setText(holdClient.getClientPhone());
        postalfield.setText(holdClient.getCustomerPostal());
/**
 *  once the country is selected sets all the first division data with
 */
        for (Countries c : countryCombo.getItems()){
            if (holdClient.getCountryId()==c.getCountry_Id()){
                countryCombo.setValue(c);
                break;
            }

        }
/**
 * once a country is selected it sets the regions with appropriate data.
 */
        regionCombo.setItems(getAllRegionsByCountry(holdClient.getCountryId()));
        for (String c : regionCombo.getItems()){
            if (Objects.equals(holdClient.getDivision(), c)){
                regionCombo.setValue(c);
                break;
            }
        }
    }


    /**
     * @param actionEvent prompts are you sure you want to cancle without saving.
     * @throws IOException throws error if it cant acces the viewcustomer.fxml
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
     * @param actionEvent  when clicking the save button it check the data and saves it to the database.
     * @throws IOException throws error if it cant access viewcustomer.fxml
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        /**
         * local instance variables to do validity check
         */

        String name = namefield.getText();
        String address = addressfield.getText();
        String phone = phonefield.getText();
        String postal = postalfield.getText();
        int country = countryCombo.getSelectionModel().getSelectedItem().getCountry_Id();
        int divisionID = Countries.getDivision(regionCombo.getValue());

        /**
         * customer validity checks
         */

        if(Client.validCustomer(name,address,phone,postal) ){
            holdClient.setCustomerName(name);
            holdClient.setCustomerAddress(address);
            holdClient.setClientPhone(phone);
            holdClient.setCustomerPostal(postal);
            holdClient.setCountryId(country);
            holdClient.setDivID(divisionID);
            DBCustomer.updateCustomer(holdClient);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/viewCustomers.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Customers");
            window.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Missing ALL required fields");
            alert.setHeaderText("Fill out all fields before saving");
            alert.showAndWait();
        }
    }

    /**
     * @param actionEvent sets the region fields based off the country selections.
     */
    public void onCountrySelect(ActionEvent actionEvent) {
        Countries bp = (Countries) countryCombo.getSelectionModel().getSelectedItem();
        regionCombo.setItems(getAllRegionsByCountry( bp.getCountry_Id()));


    }


}
