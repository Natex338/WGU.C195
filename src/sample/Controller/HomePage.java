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
import sample.Utils.DBAppointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

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
    private TableView<Appointment> userAptList;
    public static Appointment modApt;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        aptIDCol.setCellValueFactory(new PropertyValueFactory<>("aptID"));
        aptTitleCol.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
        aptDescCol.setCellValueFactory(new PropertyValueFactory<>("aptDesc"));
        aptLocCol.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
        aptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/CreateApt.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Customers");
        window.show();

    }

    public void onClickReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/Reports.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Reports");
        window.show();
    }

    public void onEditApt(ActionEvent actionEvent) throws IOException {

        if (userAptList.getSelectionModel().getSelectedIndex() != -1) {
            modApt = userAptList.getSelectionModel().getSelectedItem();

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/ModifyApt.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Customers");
            window.show();
        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Please Select a Appointment to Edit");
            alert2.setContentText("No Appointment is selected!");
            alert2.showAndWait();
        }

    }

    public void onRemoveApt(ActionEvent actionEvent) throws SQLException {
        if (userAptList.getSelectionModel().getSelectedIndex()==-1){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Please Select a Appointment to remove");
            alert2.setContentText("No Appointment is selected!");
            alert2.showAndWait();
        }
        else {
            int aptID =userAptList.getSelectionModel().getSelectedItem().getAptID();
            String aptType = userAptList.getSelectionModel().getSelectedItem().getAptType();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText("Canceling appointment");
            alert.setContentText("Are you sure you want to cancel this appointment?");
            alert.showAndWait()
                    .ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        DBAppointments.deleteAppointment(userAptList.getSelectionModel().getSelectedItem());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            alert.show();
            alert.setTitle("Appointment has been Canceled");
            alert.setHeaderText("Appointment Successfully Canceled");
            alert.setContentText("Appointment ID:  " + aptID + " Type: " +aptType);
            userAptList.setItems(DBAppointments.getAllApt());
        }
    }

    public void onALLview(ActionEvent actionEvent) throws SQLException {
        userAptList.setItems(DBAppointments.getAllApt());
    }

    public void onByMonth(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment>byMonth = FXCollections.observableArrayList();

        for(Appointment c : DBAppointments.getAllApt()) {
            if (c.getStartDateTime().getMonth() == LocalDateTime.now().getMonth()) {
                byMonth.add(c);
            }
            userAptList.setItems(byMonth);
        }
    }

    public void onByWeek(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment>byWeek = FXCollections.observableArrayList();

        for(Appointment c : DBAppointments.getAllApt()) {
            if (c.getStartDateTime().isBefore(LocalDateTime.now().plusWeeks(1)) && (c.getStartDateTime().isAfter(LocalDateTime.now().minusWeeks(1))) ){
                byWeek.add(c);
            }
            userAptList.setItems(byWeek);
        }

    }
}

