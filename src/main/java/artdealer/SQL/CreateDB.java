package artdealer.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// login to db...
// mysql -uroot -p

public class CreateDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/artshop";
    static final String USER = "root";
    static final String PASSWORD = "aggY836^@zT9&";

    public void connectDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();)

        {
            String createCustomerTableSQL = "CREATE OR REPLACE TABLE Customers " +
                   "(customer_id INT AUTO_INCREMENT NOT NULL UNIQUE, " +
                   " first_name VARCHAR(50) NOT NULL, " + 
                   " last_name VARCHAR(50) NOT NULL, " + 
                   " email VARCHAR(50) NOT NULL UNIQUE,  " + 
                   " phone VARCHAR(50) NOT NULL UNIQUE, " +
                   " PRIMARY KEY ( customer_id ))"; 
        stmt.executeUpdate(createCustomerTableSQL);
        System.out.println("Created table"); 
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    
}