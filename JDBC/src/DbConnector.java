import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String URL = "jdbc:sqlite:serwis.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}