package coffeeshop.Models;

import coffeeshop.Entities.Customers.Customer;


public class Context {
    private final static Context INSTANCE = new Context();

    public static Context getInstance() {
        return INSTANCE;
    }

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }
}
