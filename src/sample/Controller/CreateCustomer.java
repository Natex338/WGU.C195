package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class CreateCustomer implements Initializable {

    public ComboBox<Countries> countryCombo;
    public ChoiceBox<String> regionCombo;
    public TextField namefield;
    public TextField addressfield;
    public TextField phonefield;
    public TextField postalfield;
    private ObservableList<Countries> allCountries= FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
           allCountries.addAll(DBCountries.getAllCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        countryCombo.setItems(allCountries);

    }

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

    public void onSaveButton(ActionEvent actionEvent) {
        String name = namefield.getText();
        String address = addressfield.getText();
        String phone = phonefield.getText();
        String postal = postalfield.getText();
        int country = countryCombo.getSelectionModel().getSelectedItem().getCountry_Id();
        int divisionID = Countries.getDivision(regionCombo.getValue());


        if(Customer.validCustomer(name,address,phone,postal) ){
            System.out.println("We good" );
            Customer newCustomer = new Customer(name,address,postal,phone,divisionID ,country);
            DBCustomer.insertCustomer(newCustomer);

        }
        else System.out.println("shit is empty");


    }


    public void onCountrySelect(ActionEvent actionEvent) {
        Countries bp = (Countries) countryCombo.getSelectionModel().getSelectedItem();
        regionCombo.setItems(getAllRegionsByCountry( bp.getCountry_Id()));

    }
}
