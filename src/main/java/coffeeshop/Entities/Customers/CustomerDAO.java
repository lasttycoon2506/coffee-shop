package coffeeshop.Entities.Customers;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class CustomerDAO implements DAO<Customer> {
    final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    final EntityManager entityManager = factory.createEntityManager();

    @Override
    public Optional<Customer> get(Integer id) {
        return null;
    }

    public List<Customer> getAll(){
        return null;
    }

    public void save(Customer customer){
        executeInsideTransaction(entityManager -> entityManager.persist(customer));
    }

    public List<Customer> listView(){
        return null;
    }

    public void update(Customer customer){

    }

    public void delete(Customer customer){

    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            action.accept(entityManager);
            transaction.commit(); 
        }
        catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
