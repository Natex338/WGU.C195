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
import sample.Model.Customer;
import sample.Model.User;
import sample.Utils.DBAppointments;
import sample.Utils.DBConnection;
import sample.Utils.DBContact;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateApt implements Initializable {


    @FXML
    private ComboBox <User> userIDcombo;
    @FXML
    private Label aptIDText;
    @FXML
    private ComboBox <Customer>customerIDCombo;
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
    private ComboBox<LocalTime> startTimeField;
    @FXML
    private ComboBox <LocalTime> endTimeField;

    private LocalTime start = LocalTime.of(8,00);
    private LocalTime end = LocalTime.of(23,00);


    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactField.setItems(DBContact.DBallcontacts());
        userIDcombo.setItems(DBConnection.getAllUsers());
        try {
            customerIDCombo.setItems(Customer.getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (start.isBefore(end.plusSeconds(1))){
            startTimeField.getItems().add(start);
            endTimeField.getItems().add(start);
            start = start.plusMinutes(5);
        }

    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        String error = "";
        if (contactField.getValue()==null| customerIDCombo.getValue()==null|userIDcombo.getValue()==null|startTimeField.getValue()==null) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Please fill in in all appointment info");
            alert2.setContentText("Missing appointment info");
            alert2.showAndWait();
        }
        else {
            String aptTitle = titleField.getText();
            String aptDesc = descriptionField.getText();
            String aptLocation = locationField.getText();
            String aptType = typeField.getText();
            LocalDateTime startDate = startDateField.getValue().atTime(startTimeField.getValue());
            LocalDateTime endDate = endDateField.getValue().atTime(endTimeField.getValue());
            int contactID = contactField.getSelectionModel().getSelectedItem().getContactID();
            int customerID = customerIDCombo.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = userIDcombo.getSelectionModel().getSelectedItem().getUserID();
            String contactName = contactField.getSelectionModel().getSelectedItem().getContactName();
            error=(Appointment.isValidApt(aptTitle,aptDesc,aptLocation,aptType,startDate,endDate));

            if (error.isEmpty()){

                Appointment a = new Appointment (aptTitle, aptDesc, aptLocation, aptType, startDate,endDate, contactID, customerID,userID,contactName);
                DBAppointments.insertAppointment(a);

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/homePage.fxml")));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.setTitle("HomePage");
                window.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Not a valid Appointment");
                alert.setHeaderText("Missing Appointment Info");
                alert.setContentText(error);
                alert.showAndWait();
            }

        }

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
