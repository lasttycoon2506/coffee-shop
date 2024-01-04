package coffeeshop.Models;

import coffeeshop.Entities.Customers.Customer;

//stores logged-in customer obj
public class Context {
    private final static Context INSTANCE = new Context();

    public static Context getInstance() {
        return INSTANCE;
    }

    private Customer customer = new Customer();

    public void setCustomer(Customer cust) {
        this.customer = cust;
    }

    public Customer getCustomer() {
        return customer;
    }
}
