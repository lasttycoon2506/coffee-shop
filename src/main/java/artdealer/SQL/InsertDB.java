package artdealer.SQL;

import java.sql.SQLException;
import java.sql.Statement;
import artdealer.App;
import artdealer.Models.CustomerDTO;
public class InsertDB {
    
    public void InsertCustomerData(CustomerDTO customerdto) throws SQLException {
        Statement stmt = App.connection.createStatement();


        String insertCustomerSQL = "INSERT INTO Customers (first_name, last_name, email, phone)" +
                            " VALUES (@fname, @lname, @email, @phone ) "; 
                           
                            SqlCommand.Parameters
        using (var command = new SqlCommand(query, connection))
        {
            command.Parameters.AddWithValue("@name", name);
            command.Parameters.AddWithValue("@phone", phone);
        
            command.ExecuteNonQuery();
                            }
                stmt.executeUpdate(insertCustomerSQL);
    }
}
