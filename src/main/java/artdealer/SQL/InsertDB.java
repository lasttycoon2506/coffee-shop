package artdealer.SQL;

import java.sql.SQLException;
import java.sql.Statement;
import artdealer.App;
import artdealer.Models.CustomerDTO;
public class InsertDB {
    
    public void InsertCustomerData(CustomerDTO customerdto) throws SQLException {
        Statement stmt = App.connection.createStatement();

//         -- INSERT INTO Customers (first_name, last_name, email, phone) 
// -- VALUES ('Chrissy', 'Rod', 'crod@crod.com', '263-643-4726'),
// -- ('Jimbo', 'Fisher', 'jfish@jfish.com', '265-783-6846'),
// -- ('Gibby', 'Loo', 'gibbloo@gloo.com', '583-376-8603');


        String insertCustomerSQL = "INSERT INTO Customers (first_name, last_name, email, phone)" +
                            " VALUES () "; 
                           
                stmt.executeUpdate(insertCustomerSQL);
    }
}
