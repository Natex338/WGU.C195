package sample.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.Appointment;
import sample.Model.Contact;
import sample.Model.Countries;
import sample.Utils.DBAppointments;
import sample.Utils.DBContact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyApt implements Initializable {
    @FXML
    private TextField userIdField;
    @FXML
    private Label aptIDText;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox <Contact>contactField;
    @FXML
    private TextField locationField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;
    @FXML
    private Spinner startTimeField;
    @FXML
    private Spinner endTimeField;
    private Appointment holdApt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactField.setItems(DBContact.DBallcontacts());
        holdApt = HomePage.modApt;

        aptIDText.setText(String.valueOf(holdApt.getAptID()));
        titleField.setText(holdApt.getAptTitle());
        descriptionField.setText(holdApt.getAptDesc());
        locationField.setText(holdApt.getAptLocation());
        typeField.setText(holdApt.getAptType());
        startDateField.setValue(holdApt.getStartDateTime().toLocalDate());
        startTimeField.setPromptText(holdApt.getStartDateTime().toLocalTime().toString());
        endDateField.setValue(holdApt.getEndDateTime().toLocalDate());
        endTimeField.setPromptText(holdApt.getEndDateTime().toLocalTime().toString());
        userIdField.setText(String.valueOf(holdApt.getUserID()));
        customerIdField.setText(String.valueOf(holdApt.getCustomerID()));


        for (Contact c : contactField.getItems()) {
            if (holdApt.getContactID() == c.getContactID()) {
                contactField.setValue(c);
                break;
            }
        }

    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {

        LocalDateTime date = startDateField.getValue().atTime(LocalTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.format(formatter);

        holdApt.setAptTitle(titleField.getText());
        holdApt.setAptDesc(descriptionField.getText());
        holdApt.setAptLocation(locationField.getText());
        holdApt.setAptType(typeField.getText());
        holdApt.setStartDate(date);
        holdApt.setEndDate(date);
        holdApt.setContactID(contactField.getSelectionModel().getSelectedItem().getContactID());
        holdApt.setCustomerID(Integer.parseInt(customerIdField.getText()));
        holdApt.setUserID(Integer.parseInt(userIdField.getText()));


        DBAppointments.updateAppointment(holdApt);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/homePage.fxml")));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HomePage");
        window.show();

    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Unsaved Appointment");
        exitAlert.setHeaderText("Confirm Exit");
        exitAlert.setContentText("Are you sure you want to close the the Appointment without saving?");
        Optional<ButtonType> result = exitAlert.showAndWait();
        if(result.get()==ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/homePage.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("HomePage");
            window.show();
        }
    }
}
