import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        // Connection details for local Oracle 23ai
        String url = "jdbc:oracle:thin:@//localhost:1521/FREE";
        String user = "SYSTEM";
        String password = "Bunny@007"; 

        System.out.println("Connecting to Oracle Database...");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("SUCCESS! Connected to Oracle Database 23ai successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}