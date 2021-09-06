package sample.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointment;
import sample.Model.Countries;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class DBAppointments {

    public static ObservableList<Appointment> getAllApt() throws SQLException {
        ObservableList<Appointment> DBallAppointments = FXCollections.observableArrayList();
        try {
            Statement statement = DBQuery.getStatement();
            String getAllCustQuery = "SELECT * FROM appointments;";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                int aptId  = result.getInt("Appointment_ID");
                String aptTitle= result.getString("Title");
                String aptDesc= result.getString("Description");
                String aptLocation=result.getString("Location");
                String aptType = result.getString("Type");
                String startDate = result.getString("Start");
                String endDate = result.getString("End");
                int contactID=result.getInt("Contact_ID");
                int customerID=result.getInt("Customer_ID");
                int userID=result.getInt("User_ID");
                Appointment a = new Appointment(aptId,aptTitle,aptDesc,aptLocation,aptType,startDate,endDate,contactID,customerID,userID);
                DBallAppointments.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return DBallAppointments;
    }
}
