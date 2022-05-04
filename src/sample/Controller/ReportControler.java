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
import sample.Model.Client;
import sample.Model.Contact;
import sample.Model.Reports;
import sample.Utils.DBAppointments;
import sample.Utils.DBContact;
import sample.Utils.DBCustomer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * reports controller
 */
public class ReportControler implements Initializable {

    @FXML
    private TableView<Appointment> byLocationView;
    @FXML
    private ComboBox<String> locationCombo;
    @FXML
    private TableColumn<Reports, Month> aptMonth;
    @FXML
    private TableColumn<Reports, Integer> aptcount;
    @FXML
    private TableView<Reports> aptByMonthView;
    @FXML
    private TableColumn<Reports, String> aptByclientField;
    @FXML
    private TableColumn <Reports, Integer>byClientCount;
    public TableView<Reports> AptByTypeView;
    @FXML
    private TableView<Appointment> aptByContact;
    public Button onHomePage;
    @FXML
    private ChoiceBox <Contact> contactCombo;
    @FXML
    private TableColumn<Appointment, Integer> aptIDCol;
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
    private TableColumn <Appointment, Integer>aptIDCol1;
    @FXML
    private TableColumn <Appointment, String>aptTitleCol1;
    @FXML
    private TableColumn <Appointment, String>aptDescCol1;
    @FXML
    private TableColumn <Appointment, Integer> aptContactCol1;
    @FXML
    private TableColumn <Appointment, String>aptTypeCol1;
    @FXML
    private TableColumn <Appointment, String> aptSDateCol1;
    @FXML
    private TableColumn <Appointment, String> aptEDateCol1;
    @FXML
    private TableColumn <Appointment, Integer> aptCustIdCol1;

    /**
     * @param url  gets all the data for the reports
     * @param resourceBundle sets all the data gathered for the reports
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * gets and sets the locations combo box.
         */

        ObservableList<String> listocationList= FXCollections.observableArrayList();
        try {
            for (Client a : DBCustomer.getAllCustomers()) {
                listocationList.add(a.getCustomerName());
            }
            locationCombo.setItems(listocationList);
        }
            catch (SQLException e) {
                e.printStackTrace();
            }
/**
 * gets and sets the contact selection combo box.
 * sets the appointment by type table view.
 * sets the appointment view by month table view.
 */

            contactCombo.setItems(DBContact.DBallcontacts());
           // AptByTypeView.setItems(DBAppointments.getReportDataType());
            aptByMonthView.setItems(DBAppointments.getReportDataMonth());


        /**
         * sets all the table views columns and with correct data.
         */
            aptByclientField.setCellValueFactory(new PropertyValueFactory<>("clientName"));
            aptMonth.setCellValueFactory(new PropertyValueFactory<>("monthCount"));
            aptcount.setCellValueFactory(new PropertyValueFactory<>("typeCount"));
            aptIDCol.setCellValueFactory(new PropertyValueFactory<>("aptID"));
            aptTitleCol.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
            aptDescCol.setCellValueFactory(new PropertyValueFactory<>("aptDesc"));
            aptLocCol.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
            aptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            aptTypeCol.setCellValueFactory(new PropertyValueFactory<>("aptType"));
            aptSDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            aptEDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            aptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            aptIDCol1.setCellValueFactory(new PropertyValueFactory<>("aptID"));
            aptTitleCol1.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
            aptDescCol1.setCellValueFactory(new PropertyValueFactory<>("aptDesc"));
            aptContactCol1.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            aptTypeCol1.setCellValueFactory(new PropertyValueFactory<>("aptType"));
            aptSDateCol1.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            aptEDateCol1.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            aptCustIdCol1.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        }


    /**
     * @param actionEvent takes you back to the home page.
     * @throws IOException throws error if it cant find the homepage.fxml
     */
        public void onHomePage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/homePage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HomePage");
        window.show();

    }


    /**
     * @param actionEvent when selecting the contact from the list it sets the table view.
     * @throws SQLException if it has issues accessing or writing to the database it throws error.
     */
    public void onCantactCombo(ActionEvent actionEvent) throws SQLException {
        aptByContact.setItems(DBAppointments.getAllAptbyContact(contactCombo.getSelectionModel().getSelectedItem().getContactID()));
    }


    /**
     * @param actionEvent when making location selection it assigns the table view with selection
     * @throws SQLException if it has issues accessing or writing to the database it throws error.
     */
    public void onLocationCombo(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment> listCustomers= FXCollections.observableArrayList();
        for (Appointment a : DBAppointments.getAllApt()){
                for (Client c : DBCustomer.getAllCustomers()) {
                   if(c.getCustomerName().equals(locationCombo.getSelectionModel().toString())){
                       if (a.getCustomerID()==c.getCustomerID()){
                           listCustomers.add(a);
                       }
                   }
                }
            }
        byLocationView.setItems(listCustomers);
        }
}
