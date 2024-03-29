package sample.Model;
import java.time.Month;

/**
 * Reports Class
 */
public class Reports {
    private int monthCount;
    private String month;
    private String type;
    private int typeCount;
    private String location;


    /**
     * @param mc How many Appointments in a month
     * @param m the Month of appoinment
     * @param tCount Type of appointment count.
     * @param t Type of appointment.
     */
    public Reports(int mc, String m, int tCount, String t) {
        this.monthCount=mc;
        this.month=m;
        this.typeCount=tCount;
        this.type=t;
    }

    /**
     * @param typeCount Type count of appointment
     * @param type type of appointment
     */
    public Reports(int typeCount, String type){
        this.typeCount=typeCount;
        this.type= type;
    }

    /**
     * @param location Location name
     */
    public Reports(String location){
        this.location= location;
    }

    /**
     * @param monthCount Month count of appointments
     * @param month Month of appointment.
     */
    public Reports(int monthCount, int month){
        this.monthCount=monthCount;
        this.month= Month.of(month).toString();
    }

    /**
     * @return Gets the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return gets the month
     */
    public int getMonthCount() {
        return monthCount;
    }

    /**
     * @return Gets the type count
     */
    public int getTypeCount() {
        return typeCount;
    }

    /**
     * @return gets the type of appointment
     */
    public String getType() {
        return type;
    }

    /**
     * @return Gets the month of appointment
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param location Sets the month of appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @param monthCount Sets the month count
     */
    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    /**
     * @param type Sets the Type of appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param typeCount sets the type count
     */
    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    /**
     * @param month sets the month.
     */
    public void setMonth(String month) {
        this.month = month;
    }
}
