package coffeeshop.Entities.Customers;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import coffeeshop.Models.DAO;

public class CustomerDAO implements DAO<Customer> {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void get(Customer customer) {

    }

    public List<Customer> getAll(){
        return null;
    }

    public void save(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(customer);
        tx.commit();
        session.close();
    }

    public List<Customer> listView(){
        return null;
    }

    public void update(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(customer);
        tx.commit();
        session.close();
    }

    public void delete(Customer customer){}

    @Override
    public Optional<Customer> get(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }


}
