package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Utils.DBQuery;

import java.sql.ResultSet;
import java.sql.Statement;

public class Contact {

    private int contactID;
    private String contactName;
    private String email;

    public Contact(int contactID, String contactName, String email){
        this.contactID=contactID;
        this.contactName=contactName;
        this.email=email;
    }

    public int getContactID() {
        return contactID;
    }
    public String getContactName() {
        return contactName;
    }
    public String getEmail() {
        return email;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String toString(){
        return contactName;
    }
}