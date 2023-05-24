import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public Statement conexiune() {
        Connection connection = null;
        try {
            // Provide the necessary connection details (URL, username, password)
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Replace with your database URL
            String username = "proiect_pao"; // Replace with your database username
            String password = "antonia2003"; // Replace with your database password

            // Register the JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");

            // Create the connection
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Connection successful
            System.out.println("Connected to the database!");
            return statement;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }

        return null; // Return null in case of any exceptions
    }
}
