package coffeeshop.Entities.Customers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
          () -> {
            try {
                return new Customer(null, null, null, null, null, null);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        });
    }
    
    public static List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public static Boolean userNameExists(String userName) {
        return customerDAO.userNameExists(userName);
    }

    public static Boolean emailExists(String email) {
        return customerDAO.emailExists(email);
    }

    public static Boolean phoneExists(String phone) {
        return customerDAO.phoneExists(phone);
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
