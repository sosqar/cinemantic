package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/localCinemantic";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public static Connection getConnect() throws SQLException {
       return DriverManager.getConnection(URL, USER, PASS);
    }
}
