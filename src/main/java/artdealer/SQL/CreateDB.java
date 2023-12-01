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

    public void createDB() {
        //connect to DB
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();)
        {
            String createCustomerTableSQL = "CREATE TABLE IF NOT EXISTS Customers " +
                   "(customer_id INT AUTO_INCREMENT NOT NULL UNIQUE, " +
                   " first_name VARCHAR(50) NOT NULL, " + 
                   " last_name VARCHAR(50) NOT NULL, " + 
                   " email VARCHAR(50) NOT NULL UNIQUE,  " + 
                   " phone VARCHAR(50) NOT NULL UNIQUE, " +
                   " PRIMARY KEY (customer_id))"; 
        stmt.executeUpdate(createCustomerTableSQL);

        String createEmployeeTableSQL = "CREATE TABLE IF NOT EXISTS Employees " +
                   "(employee_id INT AUTO_INCREMENT NOT NULL UNIQUE, " +
                   " first_name VARCHAR(50) NOT NULL, " + 
                   " last_name VARCHAR(50) NOT NULL, " + 
                   " email VARCHAR(50) NOT NULL UNIQUE,  " + 
                   " phone VARCHAR(50) NOT NULL UNIQUE, " +
                   " title VARCHAR(50) NOT NULL UNIQUE, " +
                   " PRIMARY KEY (employee_id))"; 
        stmt.executeUpdate(createEmployeeTableSQL);

        String createCoffeeTableSQL = "CREATE TABLE IF NOT EXISTS Coffee " +
                   "(coffee_id INT AUTO_INCREMENT NOT NULL UNIQUE, " +
                   " brand VARCHAR(50) NOT NULL, " + 
                   " coffee_name VARCHAR(50) NOT NULL, " +
                   " roast_type VARCHAR(50) NOT NULL, " + 
                   " price DECIMAL(13, 2) NOT NULL, " + 
                   " region VARCHAR(50) NOT NULL, " +
                   " coffee_size VARCHAR(50) NOT NULL, " +
                   " PRIMARY KEY (coffee_id))"; 
        stmt.executeUpdate(createCoffeeTableSQL);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    
}