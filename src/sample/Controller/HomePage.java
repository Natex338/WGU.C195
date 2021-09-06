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
import sample.Utils.DBAppointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePage implements Initializable {


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
    private TableColumn <Appointment, Integer>aptIDCol;
    @FXML
    private TableColumn <Appointment, String>aptTitleCol;
    @FXML
    private TableColumn <Appointment, String>aptDescCol;
    @FXML
    private TableColumn <Appointment, String>aptLocCol;
    @FXML
    private TableColumn <Appointment, Integer> aptContactCol;
    @FXML
    private TableColumn <Appointment, String>aptTypeCol;
    @FXML
    private TableColumn <Appointment, String> aptSDateCol;
    @FXML
    private TableColumn <Appointment, String> aptEDateCol;
    @FXML
    private TableColumn <Appointment, Integer> aptCustIdCol;
    @FXML
    private TableView userAptList;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        aptIDCol.setCellValueFactory(new PropertyValueFactory<>("aptID"));
        aptTitleCol.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
        aptDescCol.setCellValueFactory(new PropertyValueFactory<>("aptDesc"));
        aptLocCol.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
        aptContactCol.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        aptTypeCol.setCellValueFactory(new PropertyValueFactory<>("aptType"));
        aptSDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        aptEDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        aptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        try {
            userAptList.setItems(DBAppointments.getAllApt());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


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
