import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    private static final String USER = "ardhra";
    private static final String PASSWORD = "ardhra123";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed. Check JDBC URL, username, or password.");
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        getConnection(); // test connection
    }
}
