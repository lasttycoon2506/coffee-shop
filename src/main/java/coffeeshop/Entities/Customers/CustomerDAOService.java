package coffeeshop.Entities.Customers;

import java.util.List;
import java.util.Optional;


public class CustomerDAOService {
    private static CustomerDAO customerDAO = new CustomerDAO();

    public CustomerDAOService(CustomerDAO customerDAO) {
        CustomerDAOService.customerDAO = customerDAO;
    }

    public static Customer getCustomer(Integer id) {
        Optional<Customer> customer = customerDAO.get(id);
        return customer.orElseGet(
          () -> new Customer(null, null, null, null, null, null));
    }
    
    public static List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public static Boolean userNameExists(String userName) {
        return customerDAO.userNameExists(userName);
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
