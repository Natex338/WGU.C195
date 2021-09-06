package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import sample.Model.Appointment;
import sample.Model.Countries;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePage {

    @FXML
    private Button exitButton;
    @FXML
    private Button viewCustomersButton;
    @FXML
    private Button viewAddAppointments;
    @FXML
    private Button reports;
    @FXML
    private Button editApt;
    @FXML
    private Button removeApt;
    @FXML
    private TableColumn aptIDCol;
    @FXML
    private TableColumn aptTitleCol;
    @FXML
    private TableColumn aptDescCol;
    @FXML
    private TableColumn aptLocCol;
    @FXML
    private TableColumn aptContactCol;
    @FXML
    private TableColumn aptTypeCol;
    @FXML
    private TableColumn aptSDateCol;
    @FXML
    private TableColumn aptEDateCol;
    @FXML
    private TableColumn aptCustIdCol;

    public void onExitButton(ActionEvent actionEvent) {
            Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Exiting Program");
            exitAlert.setHeaderText("Confirm Exit");
            exitAlert.setContentText("Are you sure you want to close the program?");
            Optional<ButtonType> result = exitAlert.showAndWait();
            if(result.get()==ButtonType.OK)
                System.exit(0);
    }

    public void onViewCustomers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/viewCustomers.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Customers");
        window.show();
    }

    public void onVewAddApt(ActionEvent actionEvent) throws IOException {

    }

    public void onClickReports(ActionEvent actionEvent) {
    }

    public void onEditApt(ActionEvent actionEvent) {
    }

    public void onRemoveApt(ActionEvent actionEvent) {
    }
}
