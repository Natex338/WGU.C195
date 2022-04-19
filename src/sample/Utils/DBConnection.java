package sample.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Contact;
import sample.Model.User;

import java.security.PublicKey;
import java.sql.*;

/**
 * DAO for the connection
 */
public abstract class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//34.83.56.255:3306/";
    private static final String dbname = "Capstoner";
    private static final String jdbcURL= protocol + vendorName + ipAddress+ dbname;
    private static final String MYSQLJBCDriver= "com.mysql.cj.jdbc.Driver";
    private static final String userName= "Natex338";
    private static final String userPw= "CapstoneProject1358!!";
    private static Connection conn= null;
    //private static final String jdbcURL="jdbc:mysql://34.83.56.255:3306/Capstoner";

    //JavaFX settings
    //--module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics



    /**
     * @return returns the start of the connection
     */
    public static Connection startConnection(){
        try{
            Class.forName(MYSQLJBCDriver);
            conn = (DriverManager.getConnection(jdbcURL,userName, userPw));
            System.out.println("Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Closes the connection
     */
    public static void closeConnection(){
        try {
            conn.close();
            System.out.println("Closed Connection");
        }
        catch (Exception e){
            //do nothing :)
        }
    }

    /**
     * @return returns all users
     */
    public static ObservableList<User> getAllUsers(){
        ObservableList<User> DBUsers = FXCollections.observableArrayList();
        try {
            Statement statement = DBQuery.getStatement();
            String getAllusersQuery = "SELECT * FROM users;";
            ResultSet result = statement.executeQuery(getAllusersQuery);
            while (result.next()) {
                int userID  = result.getInt("User_ID");
                String UserName= result.getString("User_Name");

                User u = new User(userID,UserName);
                DBUsers.add(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return DBUsers;
    }
}
