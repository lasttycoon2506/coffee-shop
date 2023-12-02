package artdealer.SQL;

import java.sql.SQLException;
import java.sql.Statement;
import artdealer.App;
import artdealer.Models.CustomerDTO;
public class InsertDB {
    
    public void InsertCustomerData(CustomerDTO customerdto) throws SQLException {
        Statement stmt = App.connection.createStatement();

        String insertCustomerSQL = "INSERT INTO Customers (first_name, last_name, email, phone)" +
                            "VALUES ('" +customerdto.firstName()+ "', '" +customerdto.lastName()+ 
                            "', '" +customerdto.email()+ "', '" +customerdto.phone()+ "')";   
        
        stmt.executeUpdate(insertCustomerSQL);
    }
}
