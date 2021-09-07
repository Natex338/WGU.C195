package sample.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Contact;

import java.sql.ResultSet;
import java.sql.Statement;

public class DBContact {
    public static ObservableList<Contact> DBallcontacts() {
        ObservableList<Contact> DBallcontacts = FXCollections.observableArrayList();
        try {
            Statement statement = DBQuery.getStatement();
            String getAllCustQuery = "SELECT * FROM contacts;";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                int contactID  = result.getInt("Contact_ID");
                String contactName= result.getString("Contact_Name");
                String email= result.getString("Email");

                Contact a = new Contact(contactID,contactName,email);
                DBallcontacts.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return DBallcontacts;
    }
}
