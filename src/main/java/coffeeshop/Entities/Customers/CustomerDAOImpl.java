package coffeeshop.Entities.Customers;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomerDAOImpl implements CustomerDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Customer customer){}

    public List<Customer> listView(){
        return null;
    }

    public void update(Customer customer){}

    public void delete(Customer customer){}


}
