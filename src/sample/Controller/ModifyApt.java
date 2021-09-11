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
    private ComboBox <Customer> customerIDcombo;

    private LocalTime start = LocalTime.of(0,00);
    private LocalTime end = LocalTime.of(23,54);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        while (start.isBefore(end.plusSeconds(1))){
            startTimeField.getItems().add(start);
            endTimeField.getItems().add(start);
            start = start.plusMinutes(5);
        }

        contactField.setItems(DBContact.DBallcontacts());
        try {
            customerIDcombo.setItems(Customer.getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        useridCombo.setItems(DBConnection.getAllUsers());
        holdApt = HomePage.modApt;

        aptIDText.setText(String.valueOf(holdApt.getAptID()));
        titleField.setText(holdApt.getAptTitle());
        descriptionField.setText(holdApt.getAptDesc());
        locationField.setText(holdApt.getAptLocation());
        typeField.setText(holdApt.getAptType());
        startDateField.setValue(holdApt.getStartDateTime().toLocalDate());
        startTimeField.setValue(holdApt.getStartDateTime().toLocalTime());
        endDateField.setValue(holdApt.getEndDateTime().toLocalDate());
        endTimeField.setValue(holdApt.getEndDateTime().toLocalTime());

        for (Contact c : contactField.getItems()) {
            if (holdApt.getContactID() == c.getContactID()) {
                contactField.setValue(c);
                break;
            }
        }
        try {
            for (Customer c : Customer.getCustomers()){
                if (holdApt.getCustomerID()==c.getCustomerID()){
                    customerIDcombo.setValue(c);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (User u :DBConnection.getAllUsers()){
            if (holdApt.getUserID()==u.getUserID()){
                useridCombo.setValue(u);
                break;
            }
        }


    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
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
        else if (!error.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not a valid Appointment");
            alert.setHeaderText("Missing Appointment Info");
            alert.setContentText(error);
            alert.showAndWait();
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
