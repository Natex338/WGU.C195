package sample.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.User;
import sample.Utils.DBQuery;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class LoginScreen {
    public Label zoneIDField;
    public Label languageField;
    public PasswordField passwordBox;
    public TextField userNameBox;
    public Button loginButton;
    public static User loggedInUser;

    @FXML

    public void onLoginButton(ActionEvent actionEvent) throws SQLException, IOException {
        if (loginCheck(userNameBox.getText(),passwordBox.getText())){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/View/homePage.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("HomePage");
            window.show();
        }
        else
            System.out.println("you failed BITCH!");
    }

    public void onCancelButton(ActionEvent actionEvent) {
        Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exiting Program");
        exitAlert.setHeaderText("Confirm Exit");
        exitAlert.setContentText("Are you sure you want to close the program?");
        Optional<ButtonType> result = exitAlert.showAndWait();
        if(result.get()==ButtonType.OK)
            System.exit(0);
    }

    public boolean loginCheck(String userName, String password) throws SQLException {
        Statement statement = DBQuery.getStatement();
        String userQuery = "SELECT password, User_ID, User_Name FROM users WHERE User_Name ='" + userName + "'";;
        ResultSet result=statement.executeQuery(userQuery);
        while (result.next()){
            if (result.getString("password").equals(password)){
                loggedInUser = new User((Integer.parseInt(result.getString("User_ID"))),userName,password);
                return true;

            }
        }
        return false;
    }
}