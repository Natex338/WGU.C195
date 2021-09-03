package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import sample.Model.Countries;
import sample.Utils.DBCountries;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Utils.DBCountries.getAllRegionsByCountry;

public class CreateCustomer implements Initializable {

    public ComboBox countryCombo;
    public ChoiceBox regionCombo;
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

    }


    public void onCountrySelect(ActionEvent actionEvent) {
        Countries bp = (Countries) countryCombo.getSelectionModel().getSelectedItem();
        regionCombo.setItems(getAllRegionsByCountry( bp.getCountry_Id()));

    }
}
