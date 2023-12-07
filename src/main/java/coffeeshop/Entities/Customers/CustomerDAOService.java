package coffeeshop.Entities.Customers;

import java.util.List;
import java.util.Optional;
import coffeeshop.Models.DAO;


public class CustomerDAOService {
    private static DAO<Customer> customerDAO;

    public static Customer getCustomer(Integer id) {
        Optional<Customer> customer = customerDAO.get(id);
        
        return customer.orElseGet(
          () -> new Customer());
    }
    
    public static List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
    
    public static void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }
    
    public static void saveCustomer(Customer customer) {
        customerDAO.save(customer);
    }
    
    public static void deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
    }
}
