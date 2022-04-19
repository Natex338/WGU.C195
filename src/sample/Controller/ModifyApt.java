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
import sample.Model.*;
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
 * Modify appointment controler and fields.
 */
public class ModifyApt implements Initializable {


    @FXML
    private Label aptIDText;
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
    private Appointment holdApt;
    @FXML
    private ComboBox <User> useridCombo;
    @FXML
    private ComboBox <Client> customerIDcombo;

    /**
     * generates the time fields with local time data.
     */
    private LocalTime start = LocalTime.of(0,00);
    private LocalTime end = LocalTime.of(23,54);

    /**
     * @param url sets the time fields with the local time data generated.
     * @param resourceBundle populates all the appointment info
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/**
 * increments the time data for the time fields
 */
        while (start.isBefore(end.plusSeconds(1))){
            startTimeField.getItems().add(start);
            endTimeField.getItems().add(start);
            start = start.plusMinutes(5);
        }
/**
 * gets all the ccontacts and sets them to the contact drop down.
 */
        contactField.setItems(DBContact.DBallcontacts());
        try {
            customerIDcombo.setItems(Client.getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * sets the userID combo field with all user ids
         * sets the appointment that is going to be modified.
         */
        useridCombo.setItems(DBConnection.getAllUsers());
        holdApt = HomePage.modApt;

/**
 * from the appointment that was selected to be modified, sets all the data fields with the data from the appointment to be modified.
 */
        aptIDText.setText(String.valueOf(holdApt.getAptID()));
        titleField.setText(holdApt.getAptTitle());
        descriptionField.setText(holdApt.getAptDesc());
        locationField.setText(holdApt.getAptLocation());
        typeField.setText(holdApt.getAptType());
        startDateField.setValue(holdApt.getStartDateTime().toLocalDate());
        startTimeField.setValue(holdApt.getStartDateTime().toLocalTime());
        endDateField.setValue(holdApt.getEndDateTime().toLocalDate());
        endTimeField.setValue(holdApt.getEndDateTime().toLocalTime());

        /**
         * checks all the contacts for the contact IDs that match the one selected
         */
        for (Contact c : contactField.getItems()) {
            if (holdApt.getContactID() == c.getContactID()) {
                contactField.setValue(c);
                break;
            }
        }
        /**
         * Checks the customers for the customer ID that match the one selected.
         */
        try {
            for (Client c : Client.getCustomers()){
                if (holdApt.getCustomerID()==c.getCustomerID()){
                    customerIDcombo.setValue(c);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * Checks all the users for the ones that is selected.
         */
        for (User u :DBConnection.getAllUsers()){
            if (holdApt.getUserID()==u.getUserID()){
                useridCombo.setValue(u);
                break;
            }
        }


    }

    /**
     * @param actionEvent when save is clicked it sets gets all the data fields and does appointment checks.
     * @throws SQLException throws error when it has issues with the database access
     * @throws IOException throws error when it cant access homepage.fxml
     */
    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {

        /**
         * gets and sets instance variable's for processing and validity check
         */
        String error;
        String title= titleField.getText();
        String description=descriptionField.getText();
        String location= locationField.getText();
        String type = typeField.getText();
        LocalDateTime sTime= startDateField.getValue().atTime(startTimeField.getValue());
        LocalDateTime eTime =endDateField.getValue().atTime(endTimeField.getValue());
        int contactID = contactField.getSelectionModel().getSelectedItem().getContactID();
        int customerID = customerIDcombo.getSelectionModel().getSelectedItem().getCustomerID();
        int userID = useridCombo.getSelectionModel().getSelectedItem().getUserID();


        /**
         * verifies all the data fields have data, also checks to make sure the appointment don't overlap.
         */
        error= Appointment.isValidApt(title,description,location,type,sTime,eTime);
        boolean overlapCheck = DBAppointments.appointmentOverlap(sTime,eTime, holdApt.getAptID(),customerID);
        if (DBAppointments.estCheck(sTime,eTime)&& error.isEmpty() &&overlapCheck){
            holdApt.setAptTitle(title);
            holdApt.setAptDesc(description);
            holdApt.setAptLocation(location);
            holdApt.setAptType(type);
            holdApt.setStartDate(sTime);
            holdApt.setEndDate(eTime);
            holdApt.setContactID(contactID);
            holdApt.setCustomerID(customerID);
            holdApt.setUserID(userID);
            DBAppointments.updateAppointment(holdApt);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/homePage.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("HomePage");
            window.show();
        }
        /**
         * if data is missing prompts that it can't save to missing appointment info.
         */
        else if (!error.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not a valid Appointment");
            alert.setHeaderText("Missing Appointment Info");
            alert.setContentText(error);
            alert.showAndWait();
        }


    }

    /**
     * @param actionEvent prompts to make sure you want to cancel saving the appointment.
     * @throws IOException throws error if it can't access the home page.
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
