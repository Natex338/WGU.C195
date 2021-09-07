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
import sample.Utils.DBAppointments;
import sample.Utils.DBContact;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateApt implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactField.setItems(DBContact.DBallcontacts());
    }

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {

        LocalDateTime date = startDateField.getValue().atTime(LocalTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.format(formatter);

        String aptTitle= titleField.getText();
        String aptDesc= descriptionField.getText();
        String aptLocation=locationField.getText();
        String aptType = typeField.getText();
        LocalDateTime startDate = date;
        LocalDateTime endDate = date;
        int contactID=contactField.getSelectionModel().getSelectedItem().getContactID();
        int customerID= Integer.parseInt(customerIdField.getText());
        int userID= Integer.parseInt(userIdField.getText());

        Appointment a = new Appointment (aptTitle, aptDesc, aptLocation, aptType, startDate,endDate, contactID, customerID,userID);
        DBAppointments.insertAppointment(a);

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
