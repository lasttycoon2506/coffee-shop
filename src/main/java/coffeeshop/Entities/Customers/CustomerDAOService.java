package coffeeshop.Entities.Customers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import coffeeshop.Models.Context;
import coffeeshop.Models.CustomerDTO;


public class CustomerDAOService {
    private static CustomerDAO customerDAO = new CustomerDAO();

    private CustomerDAOService(CustomerDAO customerDAO) {
        CustomerDAOService.customerDAO = customerDAO;
    }

    private static Customer getCustomer(Integer id) {
        Optional<Customer> customer = customerDAO.get(id);
        return customer.orElseGet(
          () -> {
            return null;
        });
    }

    public static boolean login(String userName, String pw) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        return CustomerDAO.login(userName, pw);
    }
    
    public static List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public static boolean userNameExists(String userName) {
        return CustomerDAO.userNameExists(userName);
    }

    public static boolean emailExists(String email) {
        return CustomerDAO.emailExists(email);
    }

    public static boolean phoneExists(String phone) {
        return CustomerDAO.phoneExists(phone);
    }

    public static void saveCustomer(CustomerDTO customerDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer newCustomer = new Customer(customerDTO.userName(), customerDTO.password(), customerDTO.firstName(), 
                                                        customerDTO.lastName(), customerDTO.email(), customerDTO.phone());
        customerDAO.save(newCustomer);
    }
    
    public static void editCustomer() {
        customerDAO.edit(Context.getInstance().getCustomer());
    }
    
    public static void deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
    }
}
