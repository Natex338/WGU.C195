package sample.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Countries;
import sample.Model.Customer;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class DBCountries {

    private static ObservableList<Countries> DBallCountries = FXCollections.observableArrayList();


    public static ObservableList<Countries> getAllCountries() throws SQLException {
        try {
            Statement statement = DBQuery.getStatement();

            String getAllCustQuery = "SELECT * FROM WJ07MYB.countries;";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                int countryID = result.getInt("Country_ID");
                String country = result.getString("Country");
                Timestamp createdDate = result.getTimestamp("Create_Date");
                String createdBy = result.getString("Created_By");
                Timestamp lastUpdate = result.getTimestamp("Last_Update");
                String lastUpdateBy = result.getString("Last_Updated_By");
                Countries c = new Countries(countryID,country,createdDate,createdBy,lastUpdate,lastUpdateBy);
                DBallCountries.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return DBallCountries;
    }

    public void setAllCountries(ObservableList<Countries> allCountries) {

        this.DBallCountries = allCountries;
    }

    public static ObservableList<String> getAllRegionsByCountry  (int cID){
     ObservableList<String>regionsList = FXCollections.observableArrayList();
        try {
            Statement statement = DBQuery.getStatement();

            String getAllCustQuery = "SELECT * FROM WJ07MYB.first_level_divisions";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                if (result.getInt("Country_ID")==cID){

                    String name= result.getString("Division");
                    int divisionID= result.getInt("Division_ID");
                    regionsList.add(name);
                    Countries.setRegionHashMap(name, divisionID);

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return regionsList;
    }





}
