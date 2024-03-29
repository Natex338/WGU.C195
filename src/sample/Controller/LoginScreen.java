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
import sample.Model.User;
import sample.Utils.DBAppointments;
import sample.Utils.DBQuery;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Loggin screen controller with controller fields
 */
public class LoginScreen implements Initializable {
    @FXML
    private Button cancel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label zoneIDField;
    @FXML
    private Label languageField;
    @FXML
    private PasswordField passwordBox;
    @FXML
    private TextField userNameBox;
    @FXML
    private Button loginButton;
    @FXML
    private boolean loginSuccessfull;
    public static User loggedInUser;

    ResourceBundle rb;

    /**
     * @param url loads the data fields and sets the language setting of the local computer
     * @param resourceBundle gets the resource bundles for the languages.
     */
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

/**
 * gets the Local of the computer, sets the languages
 */

        if (Locale.getDefault().getLanguage().equals("fr")||Locale.getDefault().getLanguage().equals("en"))
        try {
           rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
           languageField.setText(Locale.getDefault().getLanguage());
           zoneIDField.setText(ZoneId.systemDefault().getId());
           passwordLabel.setText(rb.getString("Password"));
           usernameLabel.setText(rb.getString("UserName"));
           loginButton.setText(rb.getString("login"));
           cancel.setText(rb.getString("cancel"));
           userNameBox.setPromptText(rb.getString("UserName"));
           passwordBox.setPromptText(rb.getString("Password"));



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param actionEvent  on clicking the login button it checks username and password, the logs the user login attempt. also checks for any upcoming appointments.
     * @throws SQLException throw error if there is an issue writing to the database
     * @throws IOException throws error if it cant find the homepage.Fxml
     */
    public void onLoginButton(ActionEvent actionEvent) throws SQLException, IOException {

        if (loginCheck(userNameBox.getText(), passwordBox.getText())) {
            try {
                aptCheck();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/homePage.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("HomePage");
            window.show();
        }

    }

    /**
     * @param actionEvent prompts you in english or French if you are want to cancel the appointment.
     */
    public void onCancelButton(ActionEvent actionEvent) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle(rb.getString("title"));
        exitAlert.setHeaderText(rb.getString("headerText"));
        exitAlert.setContentText(rb.getString("content"));
        Optional<ButtonType> result = exitAlert.showAndWait();
        if (result.get() == ButtonType.OK)
            System.exit(0);
    }

    /**
     * @param userName Username text field
     * @param password Password text field
     * @return returns true if the login is successful, false if it is not and both log the attempt in the login_Activity.txt
     * @throws SQLException throws error if having issues accessing the Database.
     * @throws FileNotFoundException throws error if it can't find the login_Activity.txt
     */
    public boolean loginCheck(String userName, String password) throws SQLException, FileNotFoundException {
        Statement statement = DBQuery.getStatement();
        String userQuery = "SELECT password, User_ID, User_Name FROM users WHERE User_Name ='" + userName + "'";        ;
        ResultSet result = statement.executeQuery(userQuery);
        while (result.next()) {
            if (result.getString("password").equals(password)) {
                loggedInUser = new User((Integer.parseInt(result.getString("User_ID"))), userName, password);
                loginSuccessfull=true;
                loginActivity();
                return true;
            }
        }

        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("badLogin"));
        alert.setHeaderText(rb.getString("badLogin"));
        alert.setContentText(rb.getString("tryagain"));
        Optional<ButtonType> result3 = alert.showAndWait();
        loginSuccessfull=false;
        loginActivity();
        return false;
    }


    /**
     * logs all the login attempts to login_Activity.txt
     * @throws FileNotFoundException throws error if it can't find the file.
     */
    public void loginActivity() throws FileNotFoundException {
        /**
         * creates the login file path, gets local date and time and formats it to readable.
         */
        String fileName="login_Activity.txt", items;
        LocalDateTime now = LocalDateTime.now();
        DBAppointments.dateTimeFormat(now);

/**
 *  creates the print writer and writes the data to the textfile.
 */
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileName),true ));

        if (loginSuccessfull){
            items = "User: " +loggedInUser.getUserName()+ " Logged in Successfully @" + DBAppointments.dateTimeFormat(now)+"\n";
        }
        else {
            items = "User: "+ userNameBox.getText() +" gave invalid Login @ " + DBAppointments.dateTimeFormat(now)+"\n";
        }
        pw.append(items);
        pw.close();
    }

    /**
     * Checks the database for any appointments coming up.
     * @throws SQLException throws error if it can't access the database or has issues.
     */
    public void aptCheck() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        int i=0;
        for (Appointment c :DBAppointments.getAllApt()){
            if (c.getStartDateTime().toLocalDate().equals(now.toLocalDate())) {
                if (c.getStartDateTime().isAfter(LocalDateTime.now()) && c.getStartDateTime().isBefore(LocalDateTime.now().plusMinutes(15)))   {
                    Alert apt = new Alert(Alert.AlertType.INFORMATION);
                    apt.setTitle("You have a appointment coming up!");
                    apt.setContentText("Appointment at: " + c.getStartDateTime() + " \n" + " Appointment ID:" + c.getAptID() + " \n Appointment Description:" + c.getAptDesc());
                    apt.showAndWait();
                    i++;
                }
            }
        }
        /**
         * if there aren't any appointments prompts and says there isn't any.
         */
        if (i==0){
                Alert apt = new Alert(Alert.AlertType.INFORMATION);
                apt.setTitle("You have no up coming appointments!");
                apt.setHeaderText("There are no up coming Appointments");
                apt.showAndWait();
            }
        }
    }