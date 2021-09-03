package sample.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Countries;
import sample.Model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;

public class DBCountries {

    private static ObservableList<Countries> DBallCountries = FXCollections.observableArrayList();
    private static HashMap regionMap = new HashMap();


    public static ObservableList<Countries> getAllCountries() throws SQLException {
        try {
            Statement statement = DBQuery.getStatement();

            String getAllCustQuery = "Country, countries.Create_Date,countries.Created_By, countries.Last_Update, countries.Last_Updated_By, first_level_divisions.Division, first_level_divisions.COUNTRY_ID as \"Division_CountryID\"  from first_level_divisions,countries where first_level_divisions.COUNTRY_ID =countries.Country_ID";
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
                System.out.println(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Done with Countries list");

        return DBallCountries;
    }

    public void setAllCountries(ObservableList<Countries> allCountries) {

        this.DBallCountries = allCountries;
    }

    public static HashMap getAllRegionsByCountry  (int cID){

        try {
            Statement statement = DBQuery.getStatement();

            String getAllCustQuery = "SELECT * FROM WJ07MYB.first_level_divisions";
            ResultSet result = statement.executeQuery(getAllCustQuery);
            while (result.next()) {
                if (result.getInt("Country_ID")==cID){
                    regionMap.put(result.getString("Division"),cID);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(regionMap);

        return regionMap;
    }





}
