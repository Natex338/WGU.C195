package sample.Controller;

import javafx.beans.value.ObservableObjectValue;
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
import sample.Model.Contact;
import sample.Model.Customer;
import sample.Model.Reports;
import sample.Utils.DBAppointments;
import sample.Utils.DBContact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportControler implements Initializable {

    @FXML
    private TableView<Appointment> byLocationView;
    @FXML
    private ComboBox<String> locationCombo;
    @FXML
    private TableColumn<Reports, Month> aptMonth;
    @FXML
    private TableColumn<Reports, Integer> count;
    @FXML
    private TableView<Reports> aptByMonthView;
    @FXML
    private TableColumn<Reports, String> aptByTypeField;
    @FXML
    private TableColumn <Reports, Integer>byTypeCount;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listocationList= FXCollections.observableArrayList();
        try {
            for (Appointment a : DBAppointments.getAllApt()) {
                listocationList.add(a.getAptLocation());
            }
            locationCombo.setItems(listocationList);
        }
            catch (SQLException e) {
                e.printStackTrace();
            }


            contactCombo.setItems(DBContact.DBallcontacts());
            AptByTypeView.setItems(DBAppointments.getReportDataType());
            aptByMonthView.setItems(DBAppointments.getReportDataMonth());

            aptByTypeField.setCellValueFactory(new PropertyValueFactory<>("type"));
            byTypeCount.setCellValueFactory(new PropertyValueFactory<>("typeCount"));


            aptMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
            count.setCellValueFactory(new PropertyValueFactory<>("monthCount"));

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


        public void onHomePage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/homePage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HomePage");
        window.show();

    }


    public void onCantactCombo(ActionEvent actionEvent) throws SQLException {
        aptByContact.setItems(DBAppointments.getAllAptbyContact(contactCombo.getSelectionModel().getSelectedItem().getContactID()));
    }


    public void onLocationCombo(ActionEvent actionEvent) throws SQLException {
        for (String s : locationCombo.getItems()){
            if (locationCombo.getSelectionModel().getSelectedItem().equals(s)){
                byLocationView.setItems(DBAppointments.getAllAptbyLocation(s));
                break;
            }
        }




    }
}
