package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.Customer;
import sample.Utils.DBConnection;
import sample.Utils.DBQuery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    /**
     * @param stage Start the application and load the Login screen
     * @throws IOException throws error if it cant find the loginScreen.Fxml
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * opens the connection the database and closes it also launches the application.
     */
    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);
        launch(args);
        DBConnection.closeConnection();
    }
}
