import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Get a database connection
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Fetch employee data
    public static ResultSet getEmployeeData(Connection connection) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id, first_name, last_name, role_id FROM employee");
        return statement.executeQuery();
    }
}