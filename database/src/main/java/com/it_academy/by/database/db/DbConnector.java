package com.it_academy.by.database.db;

import java.sql.*;

public class DbConnector {

    private static String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "root";
    private static String password = "oil_lion456sky";
    private static String DbDriver = "com.mysql.jdbc.Driver";

    private static Connection dbConnection = null;

    public static Connection getDBConnection() {
        try {
            Class.forName(DbDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver ERROR:" + e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(url, username,password);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return dbConnection;
    }

    private void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}