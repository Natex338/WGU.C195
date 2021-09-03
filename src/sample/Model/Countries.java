package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class Countries {
    private int country_Id;
    private String country;
    private Timestamp createDate;
    private String created_By;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private static ObservableList<Countries> countries = FXCollections.observableArrayList();


    public Countries(int countryID, String country, Timestamp createdDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.country_Id = countryID;
        this.country = country;
        this.createDate = createdDate;
        this.created_By = createdBy;
        this.lastUpdated = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public static ObservableList<sample.Model.Countries> getCountries() throws SQLException {
        return countries;
    }

    public int getCountry_Id() {
        return country_Id;
    }

    public String getCountry() {
        return country;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreated_By() {
        return created_By;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public static void setCountries(ObservableList<sample.Model.Countries> countries) {
        Countries.countries = countries;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String toString() {
        return country_Id + "- " + country;
    }
}
