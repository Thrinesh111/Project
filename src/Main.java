import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"; // Change to your database URL
    private static final String USER = "root"; // Change to your database username
    private static final String PASS = "Thrinesh@123"; // Change to your database password

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Attempt to establish a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            System.out.println("Connection to the database was successful!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        } finally {
            // Close the connection if it was established
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}