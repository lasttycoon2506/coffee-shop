package coffeeshop.Entities.Customers;

import java.util.List;
import java.util.Optional;

import coffeeshop.Models.DAO;

public class CustomerDAOService {
    private static DAO<Customer> customerDAO;

    public static Customer getUser(long id) {
        Optional<Customer> customer = customerDAO.get(id);
        
        return customer.orElseGet(
          () -> new Customer());
    }
    
    public static List<Customer> getAllUsers() {
        return customerDAO.getAll();
    }
    
    public static void updateUser(Customer customer) {
        customerDAO.update(customer);
    }
    
    public static void saveUser(Customer customer) {
        customerDAO.save(customer);
    }
    
    public static void deleteUser(Customer customer) {
        customerDAO.delete(customer);
    }
}
