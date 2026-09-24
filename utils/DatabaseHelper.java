package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    // MySQL connection settings (Update these if your username/password/database name are different)
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // change to your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        // We connect without a specific database first to create it if it doesn't exist
        String rootUrl = "jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(rootUrl, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            // Create database
            stmt.execute("CREATE DATABASE IF NOT EXISTS hospital_db");
            
            // Now switch to the created database
            stmt.execute("USE hospital_db");

            // Create tables
            stmt.execute("CREATE TABLE IF NOT EXISTS patients (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), age INT, symptom VARCHAR(255))");
            stmt.execute("CREATE TABLE IF NOT EXISTS doctors (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), specialty VARCHAR(255), available_slots VARCHAR(500))");
            stmt.execute("CREATE TABLE IF NOT EXISTS appointments (id VARCHAR(255) PRIMARY KEY, patient_id VARCHAR(255), doctor_id VARCHAR(255), slot VARCHAR(255))");
            stmt.execute("CREATE TABLE IF NOT EXISTS bills (id VARCHAR(255) PRIMARY KEY, patient_name VARCHAR(255), doctor_name VARCHAR(255), slot VARCHAR(255), consultation_fee DOUBLE, service_charge DOUBLE, total DOUBLE)");
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }
}
