package sample.Model;

import java.time.Month;

public class Reports {
    private int monthCount;
    private String month;
    private String type;
    private int typeCount;
    private String location;



    public Reports(int mc, String m, int tCount, String t) {
        this.monthCount=mc;
        this.month=m;
        this.typeCount=tCount;
        this.type=t;
    }

    public Reports(int typeCount, String type){
        this.typeCount=typeCount;
        this.type= type;
    }
    public Reports(String location){
        this.location= location;
    }

    public Reports(int monthCount, int month){
        this.monthCount=monthCount;
        this.month= Month.of(month).toString();
    }

    public String getLocation() {
        return location;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public String getType() {
        return type;
    }

    public String getMonth() {
        return month;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
