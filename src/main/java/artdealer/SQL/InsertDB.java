package artdealer.SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import artdealer.Models.CustomerDTO;


public class InsertDB {
    public void InsertCustomerData(CustomerDTO customerdto) throws SQLException {
        Connection connection = ConnectDB.ConnectDb();
        Statement stmt = connection.createStatement();

        String insertCustomerSQL = "INSERT INTO Customers (user_name, pword, first_name, last_name, email, phone)" +
                            "VALUES ('" +customerdto.firstName()+ "', '" +customerdto.lastName()+ 
                                    "', '" +customerdto.email()+ "', '" +customerdto.phone()+
                                    "', '" +customerdto.email()+ "', '" +customerdto.phone()+ "')";   
        
        stmt.executeUpdate(insertCustomerSQL);
    }
}
