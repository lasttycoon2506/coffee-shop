package coffeeshop.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// login to db...
// mysql -uroot -p
public class ConnectDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/coffeeshop";
    static final String USER = "root";
    static final String PASSWORD = "aggY836^@zT9";

    public static Connection ConnectDb() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return connection;
    }
}
