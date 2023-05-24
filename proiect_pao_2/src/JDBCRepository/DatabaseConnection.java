package JDBCRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public Statement conexiune() {
        Connection connection = null;
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "proiect_pao";
            String password = "antonia2003";


            Class.forName("oracle.jdbc.OracleDriver");


            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();


            System.out.println("Connected to the database!");
            return statement;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }

        return null;
    }
}
