package sample.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controller.LoginScreen;
import sample.Model.Appointment;
import sample.Model.Customer;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class  DBAppointments {

    public static ObservableList<Appointment> getAllApt() throws SQLException {
        ObservableList<Appointment> DBallAppointments = FXCollections.observableArrayList();
        try {
            Statement statement = DBQuery.getStatement();
            String getAllCustQuery = "SELECT * FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID;";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                int aptId  = result.getInt("Appointment_ID");
                String aptTitle= result.getString("Title");
                String aptDesc= result.getString("Description");
                String aptLocation=result.getString("Location");
                String aptType = result.getString("Type");
                LocalDateTime startDate = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDate = result.getTimestamp("End").toLocalDateTime();
                int contactID=result.getInt("Contact_ID");
                int customerID=result.getInt("Customer_ID");
                int userID=result.getInt("User_ID");
                String contactName= result.getString("Contact_Name");



                Appointment a = new Appointment(aptId,aptTitle,aptDesc,aptLocation,aptType,startDate,endDate,contactID,customerID,userID,contactName);
                DBallAppointments.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return DBallAppointments;
    }

    public static void insertAppointment(Appointment c) throws SQLException {
        String aptTitle= c.getAptTitle();
        String aptDesc= c.getAptDesc();
        String aptLocation=c.getAptLocation();
        String aptType = c.getAptType();
        Timestamp startDate = Timestamp.valueOf(c.getStartDateTime());
        Timestamp endDate = Timestamp.valueOf(c.getEndDateTime());
        int contactID=c.getContactID();
        int customerID=c.getCustomerID();
        int userID=c.getUserID();

        try {
            String insertAppointment = "INSERT INTO appointments (Title,Description,Type,Location,Start,End,Created_By,Last_Updated_By,Customer_ID,User_ID,Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = DBConnection.startConnection().prepareStatement(insertAppointment);
            ps.setString(1, aptTitle);
            ps.setString(2, aptDesc);
            ps.setString(3, aptType);
            ps.setString(4, aptLocation);
            ps.setTimestamp(5, startDate);
            ps.setTimestamp(6, endDate);
            ps.setString(7, LoginScreen.loggedInUser.getUserName());
            ps.setString(8, LoginScreen.loggedInUser.getUserName());
            ps.setInt(9, customerID);
            ps.setInt(10, userID);
            ps.setInt(11, contactID);
            ps.execute();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
    public static void deleteCustomerApt(int cID) throws SQLException {
        String deleteStatement = "DELETE FROM appointments WHERE Customer_ID = ?;";
        PreparedStatement ps = DBConnection.startConnection().prepareStatement(deleteStatement);
        ps.setInt(1,cID);
        ps.execute();

    }


    public static void deleteAppointment(Appointment c) throws SQLException {
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?;";
        PreparedStatement ps = DBConnection.startConnection().prepareStatement(deleteStatement);
        ps.setInt(1,c.getAptID());
        ps.execute();

    }

    public static String dateTimeFormat(LocalDateTime t){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return t.format(formatter);
    }

    public static void updateAppointment(Appointment appointment){
        int aptID =appointment.getAptID();
        String aptTitle =appointment.getAptTitle();
        String aptDesc = appointment.getAptDesc();
        String aptLocation = appointment.getAptLocation();
        String aptType = appointment.getAptType();
        Timestamp startDate =Timestamp.valueOf(appointment.getStartDateTime());
        Timestamp endDate = Timestamp.valueOf(appointment.getEndDateTime());
        int contactID =appointment.getContactID();
        int customerID = appointment.getCustomerID();
        int userID = appointment.getUserID();

        try {
            String UpdateAppointment = "UPDATE appointments SET  Title= ?,Description =?,Type= ?,Location=?,Start=?,End=?,Last_Updated_By=?,Customer_ID=?,User_ID=?,Contact_ID=? WHERE Appointment_ID = ? ;";
            PreparedStatement ps = DBConnection.startConnection().prepareStatement(UpdateAppointment);
            ps.setString(1,aptTitle);
            ps.setString(2,aptDesc);
            ps.setString(3,aptType);
            ps.setString(4,aptLocation);
            ps.setTimestamp(5,startDate);
            ps.setTimestamp(6,endDate);
            ps.setString(7,LoginScreen.loggedInUser.getUserName());
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);
            ps.setInt(11,aptID);
            ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
