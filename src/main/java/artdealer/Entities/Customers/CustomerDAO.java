package artdealer.Entities.Customers;

import java.util.List;

public interface CustomerDAO {
    
    public void save(Customer customer);

    public List<Customer> list();

}
