package sample.Utils;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbname = "WJ07MYB";
    private static final String jdbcURL= protocol + vendorName + ipAddress+ dbname;
    private static final String MYSQLJBCDriver= "com.mysql.cj.jdbc.Driver";
    private static final String userName= "U07MYB";
    private static final String userPw= "53689069196";
    private static Connection conn= null;


    public static Connection startConnection(){
        try{
            Class.forName(MYSQLJBCDriver);
            conn = (DriverManager.getConnection(jdbcURL,userName,userPw));
            System.out.println("Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.getStackTrace();
        }
        return conn;
    }




    public static void closeConnection(){
        try {
            conn.close();
            System.out.println("Closed Connection");
        }
        catch (Exception e){
            //do nothing :)
        }
    }
}
