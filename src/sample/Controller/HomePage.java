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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Home page after login
 */
public class HomePage implements Initializable {

    /**
     * controller fields and tables.
     */
    @FXML
    private TableColumn<Appointment, Integer> userIDField;
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
    @FXML
    private TextField searchBox;



    /**
     *  sets all the table views data and field's with appointment fields, gets all the data from the database.
     */
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
        userIDField.setCellValueFactory(new PropertyValueFactory<>("userID"));
        try {
            userAptList.setItems(DBAppointments.getAllApt());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param actionEvent Prompts you with an alert asking if you are sure you want to exit the program.
     */
    public void onExitButton(ActionEvent actionEvent) {
            Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Exiting Program");
            exitAlert.setHeaderText("Confirm Exit");
            exitAlert.setContentText("Are you sure you want to close the program?");
            Optional<ButtonType> result = exitAlert.showAndWait();
            if(result.get()==ButtonType.OK)
                System.exit(0);
    }

    /**``
     * @param actionEvent takes you to view all customers view.
     * @throws IOException throws error if it cant find the viewcustomer.fxml file.
     */
    public void onViewCustomers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/viewCustomers.fxml")));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Customers");
        window.show();
    }

    /**
     * @param actionEvent takes you to create appointment view.
     * @throws IOException throws error if it cant find the CreateApt.fxml
     */
    public void onVewAddApt(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/CreateApt.fxml")));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Customers");
        window.show();

    }

    /**
     * @param actionEvent takes you to the reports view.
     * @throws IOException throws error if it cant find the Reports.fxml
     */
    public void onClickReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/Reports.fxml")));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Reports");
        window.show();
    }

    /**
     * @param actionEvent takes you to edit an appointment screen.
     * @throws IOException throws error if it cant find the ModifyApt.fxml
     */
    public void onEditApt(ActionEvent actionEvent) throws IOException {

        if (userAptList.getSelectionModel().getSelectedIndex() != -1) {
            modApt = userAptList.getSelectionModel().getSelectedItem();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/View/ModifyApt.fxml")));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Customers");
            window.show();
        }

        /**
         *Throws error if appointment isn't selected.
         */
        else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Please Select a Appointment to Edit");
            alert2.setContentText("No Appointment is selected!");
            alert2.showAndWait();
        }

    }

    /**
     * lambda expression #1 is used to simplify the button selection
     * @param actionEvent prompts are you sure you want to cancel appointment. if yes it will remove it from the table view and remove it from the database.
     * @throws SQLException throws SQL error if there is issues removing the data.
     */


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

    /**
     * @param actionEvent Sets tabel view with all appointments.
     * @throws SQLException throws error if issue with SQL Database.
     */
    public void onALLview(ActionEvent actionEvent) throws SQLException {
        userAptList.setItems(DBAppointments.getAllApt());
    }

    /**
     * @param actionEvent set table view to show only this month's appointments.
     * @throws SQLException throws error if issue with SQL Database.
     */
    public void onByMonth(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment>byMonth = FXCollections.observableArrayList();

        for(Appointment c : DBAppointments.getAllApt()) {
            if (c.getStartDateTime().getMonth() == LocalDateTime.now().getMonth()) {
                byMonth.add(c);
            }
            userAptList.setItems(byMonth);
        }
    }

    /**
      * @param actionEvent set table view to show only this week's appointments.
      * @throws SQLException throws error if issue with SQL Database.
     */
    public void onByWeek(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment>byWeek = FXCollections.observableArrayList();

        for(Appointment c : DBAppointments.getAllApt()) {
            if (c.getStartDateTime().isBefore(LocalDateTime.now().plusWeeks(1)) && (c.getStartDateTime().isAfter(LocalDateTime.now().minusWeeks(1))) ){
                byWeek.add(c);
            }
            userAptList.setItems(byWeek);
        }

    }

    public void onSearchEnter(ActionEvent actionEvent) throws SQLException {
        String a = searchBox.getText();
        ObservableList<Appointment> apt=searchByContact(a);

        if(!apt.isEmpty()) {
            userAptList.setItems(apt);
        }
        else {
            Alert noPart =new Alert(Alert.AlertType.WARNING);
            noPart.setTitle("No Part Found!");
            noPart.setHeaderText("No Part Found!");
            noPart.setContentText("Please enter a valid part name or part ID");
            Optional<ButtonType> result = noPart.showAndWait();
        }
    }

    private ObservableList<Appointment>searchByContact(String contactName) throws SQLException {
        ObservableList<Appointment> apt = FXCollections.observableArrayList();
        ObservableList<Appointment> allAppointments = DBAppointments.getAllApt();
        for(Appointment a:allAppointments){
            if ((a.getContactName().toLowerCase().contains((contactName).toLowerCase()))){
                apt.add(a);
            }
        }
        return apt;
    }
}


