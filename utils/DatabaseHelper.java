package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Satyam@62"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found!");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        String rootUrl = "jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {}

        try (Connection conn = DriverManager.getConnection(rootUrl, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            // Create database
            stmt.execute("CREATE DATABASE IF NOT EXISTS hospital_db");
            stmt.execute("USE hospital_db");

            // Create simple tables
            stmt.execute("CREATE TABLE IF NOT EXISTS patients (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), age INT, symptom VARCHAR(255))");
            stmt.execute("CREATE TABLE IF NOT EXISTS doctors (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), specialty VARCHAR(255), available_slots VARCHAR(500))");
            stmt.execute("CREATE TABLE IF NOT EXISTS appointments (id VARCHAR(255) PRIMARY KEY, patient_id VARCHAR(255), doctor_id VARCHAR(255), slot VARCHAR(255))");
            stmt.execute("CREATE TABLE IF NOT EXISTS bills (id VARCHAR(255) PRIMARY KEY, patient_name VARCHAR(255), doctor_name VARCHAR(255), total DOUBLE, is_paid BOOLEAN)");

            // Insert default doctors if table is empty
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM doctors");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.execute("INSERT INTO doctors VALUES ('d1', 'Dr. Smith', 'Orthopedic', '10:00 AM|11:00 AM')");
                stmt.execute("INSERT INTO doctors VALUES ('d2', 'Dr. Jones', 'Cardiologist', '09:00 AM|01:00 PM')");
                stmt.execute("INSERT INTO doctors VALUES ('d3', 'Dr. Adams', 'General', '02:00 PM|04:00 PM')");
                stmt.execute("INSERT INTO doctors VALUES ('d4', 'Dr. Emily', 'Pediatrician', '11:00 AM|03:00 PM')");
                stmt.execute("INSERT INTO doctors VALUES ('d5', 'Dr. Brown', 'Neurologist', '01:00 PM|05:00 PM')");
            }
        } catch (SQLException e) {
            System.err.println("Database init error: " + e.getMessage());
        }
    }
}