package artdealer.Entities.Customers;

import java.util.List;


public interface CustomerDAO {

    public void save(Customer customer);

    public List<Customer> listView();

    public void update(Customer customer);

    public void delete(Customer customer);

}
