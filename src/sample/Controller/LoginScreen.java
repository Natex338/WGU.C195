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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


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

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void onCancelButton(ActionEvent actionEvent) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle(rb.getString("title"));
        exitAlert.setHeaderText(rb.getString("headerText"));
        exitAlert.setContentText(rb.getString("content"));
        Optional<ButtonType> result = exitAlert.showAndWait();
        if (result.get() == ButtonType.OK)
            System.exit(0);
    }

    public boolean loginCheck(String userName, String password) throws SQLException, FileNotFoundException {
        Statement statement = DBQuery.getStatement();
        String userQuery = "SELECT password, User_ID, User_Name FROM users WHERE User_Name ='" + userName + "'";
        ;
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

    public void loginActivity() throws FileNotFoundException {
        String fileName="login_Activity.txt", items;
        LocalDateTime now = LocalDateTime.now();
        DBAppointments.dateTimeFormat(now);

        PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileName),true ));

        if (loginSuccessfull){
            items = DBAppointments.dateTimeFormat(now) + " User: " +loggedInUser.getUserName()+ " Logged in Successfully\n";
        }
        else {
            items = DBAppointments.dateTimeFormat(now)  + " Failed log in Attempt\n";
        }
        pw.append(items);
        pw.close();
    }

    public void aptCheck() throws SQLException {
        LocalDateTime now = LocalDateTime.now();

        for (Appointment c :DBAppointments.getAllApt()){
            if (c.getUserID()==loggedInUser.getUserID()) {
                if (c.getStartDateTime().toLocalDate().equals(now.toLocalDate())) {
                    if (c.getStartDateTime().isAfter(LocalDateTime.now()) && c.getStartDateTime().isBefore(LocalDateTime.now().plusMinutes(15)))   {
                        Alert apt = new Alert(Alert.AlertType.INFORMATION);
                        apt.setTitle("You have a appointment coming up today");
                        apt.setContentText("Appointment at: " + c.getStartDateTime() + " \n" + " Appointment ID:" + c.getAptID() + " \n Appointment Description:" + c.getAptDesc());
                        apt.showAndWait();
                    }
                }
            }
        }
        LocalDateTime currentTime = LocalDateTime.now();

        System.out.println(currentTime);
    }
}