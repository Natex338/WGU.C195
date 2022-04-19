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
import sample.Model.Client;
import sample.Model.Contact;
import sample.Model.User;
import sample.Utils.DBAppointments;
import sample.Utils.DBConnection;
import sample.Utils.DBContact;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This Class created the appointments and handles processing the data
 */
public class CreateApt implements Initializable {

    /**
     * Class variables
     */
    @FXML
    private ComboBox <User> userIDcombo;
    @FXML
    private Label aptIDText;
    @FXML
    private ComboBox <Client>customerIDCombo;
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

    /**
     * populates the time fields with hours and mins in 5 min increments
     */
    private LocalTime start = LocalTime.of(0,00);
    private LocalTime end = LocalTime.of(23,54);


    /**
     * @param url gets the data for the Create Appointment screen.
     * @param resourceBundle sets the fields
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**
         * sets the contact, user ID and time fields with appropriate data
         */

        contactField.setItems(DBContact.DBallcontacts());
        userIDcombo.setItems(DBConnection.getAllUsers());
        try {
            customerIDCombo.setItems(Client.getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (start.isBefore(end.plusSeconds(1))){
            startTimeField.getItems().add(start);
            endTimeField.getItems().add(start);
            start = start.plusMinutes(5);
        }

    }

    /**
     * @param actionEvent logic check for saving new appointments, gets data from fields, and saves to the database.
     * @throws SQLException throws SQL error instead of breaking
     * @throws IOException hanles writing the data errors.
     */
    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {

        /**
         * checks to see if fields are empty
         */

        String error = "";
        if (contactField.getValue()==null| customerIDCombo.getValue()==null|userIDcombo.getValue()==null|startTimeField.getValue()==null|endTimeField.getValue()==null) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Please fill in in all appointment info");
            alert2.setContentText("Missing appointment info");
            alert2.showAndWait();
        }
        /**
         * if the fields are not empty it gets teh data and does a visit validity check, also check for overlaps
         */
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
            boolean overlapCheck = DBAppointments.appointmentOverlap(startDate,endDate, -1,customerID);




            /**
             * if validity check is good with no errors it creates a new appointment and saves it to the database. if not prompts with an error.
             */
            if (error.isEmpty() && DBAppointments.estCheck(startDate,endDate) && overlapCheck){

                Appointment a = new Appointment (aptTitle, aptDesc, aptLocation, aptType, startDate,endDate, contactID, customerID,userID,contactName);
                DBAppointments.insertAppointment(a);

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/homePage.fxml")));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.setTitle("HomePage");
                window.show();
            }

            else if (!error.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Not a valid Appointment");
                alert.setHeaderText("Missing Appointment Info");
                alert.setContentText(error);
                alert.showAndWait();
            }

        }

    }

    /**
     * clicking the cancle button will do a prompt to check if you are sure you want to cancel.
     */

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
