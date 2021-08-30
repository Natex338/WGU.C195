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
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnection.startConnection();
      /*  ResourceBundle rb = ResourceBundle.getBundle("/Nat", Locale.getDefault());
       {
            System.out.println(rb.getString("login") + " " + rb.getString("cancel"));
        } */
        DBQuery.setStatement(conn);
        launch(args);
        DBConnection.closeConnection();
    }
}
