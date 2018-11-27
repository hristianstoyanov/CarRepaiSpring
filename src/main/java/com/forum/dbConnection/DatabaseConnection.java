package com.forum.dbConnection;

import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Hristiyan on 14.5.2018 ã..
 */
@Component
public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/car_repair_db";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "123123";

    private Connection dbConnection;

    public DatabaseConnection() {

    }

    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.dbConnection;
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void closeConnection() {
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
