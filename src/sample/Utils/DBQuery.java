package sample.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBQuery {

    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }


    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }
    public static Statement getStatement(){
        return statement;
    }
}

