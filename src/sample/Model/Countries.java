package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Contries class
 */
public class Countries {
    private int country_Id;
    private String country;
    private Timestamp createDate;
    private String created_By;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private static ObservableList<Countries> countries = FXCollections.observableArrayList();
    private static Map<String, Integer>divisionList= new HashMap<>();


    /**
     * @param countryID Country ID
     * @param country Country Name
     * @param createdDate Created date
     * @param createdBy Created By
     * @param lastUpdate Last Updated
     * @param lastUpdatedBy Last Updated By
     */
    public Countries(int countryID, String country, Timestamp createdDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.country_Id = countryID;
        this.country = country;
        this.createDate = createdDate;
        this.created_By = createdBy;
        this.lastUpdated = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return returns all countries from the Observables
     * @throws SQLException throws error if it can't access the database.
     */
    public static ObservableList<sample.Model.Countries> getCountries() throws SQLException {
        return countries;
    }

    /**
     * @param divname Division Name
     * @return returns division ID from hash map
     */
    public static int getDivision(String divname) {
       return divisionList.get(divname);
    }

    /**
     * @return returns country ID
     */
    public int getCountry_Id() {
        return country_Id;
    }


    /**
     * @return Gets country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return gets created date
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @return gets created by string
     */
    public String getCreated_By() {
        return created_By;
    }

    /**
     * @return gets last updated by date
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @return gets last updated by string
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param countries sets the country Observable lists
     */
    public static void setCountries(ObservableList<sample.Model.Countries> countries) {
        Countries.countries = countries;
    }

    /**
     * @param country sets the country name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @param country_Id sets the country ID
     */
    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }

    /**
     * @param created_By Sets the Created by string
     */
    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    /**
     * @param createDate Sets teh created date
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @param lastUpdated sets the Last updated date with timestamp of local Datetime.
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @param lastUpdatedBy Sets the last updated by variable.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param name Name of the region
     * @param country_Id region ID
     */
    public static void setRegionHashMap(String name,int country_Id){
        divisionList.put(name,country_Id);

    }

    /**
     * @return Overiding the toString method to print country ID and Country
     */
    public String toString() {
        return country_Id + "- " + country;
    }
}
