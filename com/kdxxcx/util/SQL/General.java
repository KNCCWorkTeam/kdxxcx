package com.kdxxcx.util.SQL;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;

import static com.kdxxcx.util.KDXXCX.ExceptionDealing.SQLExceptionDealing;


public class General {
    public static void insertInto(String url,String user,String password,String command){
        Connection connection = null;
        Statement statement = null;
        try {
            Driver driver = new SQLServerDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.execute(command);
        } catch (SQLException e) {
            SQLExceptionDealing(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }

    public static ResultSet selectOut (String url, String user, String password, String command) {
        try {
            Connection connection;
            Statement statement;
            SQLServerDriver driver = new SQLServerDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            connection.prepareStatement(command, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery(command);
        } catch (SQLException e) {
            SQLExceptionDealing(e);
        }
        return null;
    }
}
