package coffeeshop.Entities.Customers;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import at.favre.lib.crypto.bcrypt.BCrypt;
import coffeeshop.Models.Context;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;


public class CustomerDAO implements DAO<Customer> {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    // private static final EntityManager entityManager = factory.createEntityManager();
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    @Override
    public Optional<Customer> get(Integer customerId) {
        EntityManager em = getEntityManager();
        return Optional.ofNullable(em.find(Customer.class, customerId));
    }
    public List<Customer> getAll(){
        return null;
    }

    public static boolean userNameExists(String userName)  {
        try {
            executeInsideTransaction(entityManager -> 
                                    entityManager.createQuery("SELECT user FROM Customer user WHERE user.userName = :userName", 
                                    Customer.class).setParameter("userName", userName).getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public static boolean emailExists(String email)  {
        try {
            executeInsideTransaction(entityManager -> 
                                    entityManager.createQuery("SELECT email FROM Customer email WHERE email.email = :email", 
                                    Customer.class).setParameter("email", email).getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public static boolean phoneExists(String phone)  {
        try {
            executeInsideTransaction(entityManager -> 
                entityManager.createQuery("SELECT phone FROM Customer phone WHERE phone.phone = :phone", 
                                        Customer.class).setParameter("phone", phone).getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public static boolean login(String user, String pw) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        EntityManager em = getEntityManager();
        Customer customer = em.createQuery("SELECT pwHash FROM Customer pwHash WHERE userName = :userName",
                            Customer.class).setParameter("userName", user).getSingleResult();
        BCrypt.Result result = BCrypt.verifyer().verify(pw.toCharArray(), customer.getPassword().trim());
        if (result.verified) {
            //sets current customer for future display/operations
            Context.getInstance().setCustomer(customer);
            return true;
        }
        else {
            return false;
        }
    }
    
    public void save(Customer customer) {
        executeInsideTransaction(entityManager -> entityManager.persist(customer));
        Context.getInstance().setCustomer(customer);
    }

    public void edit(Customer customer){
        executeInsideTransaction(entityManager -> entityManager.merge(customer));
    }

    public void delete(Customer customer){

    }

    private static void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            action.accept(em);
            transaction.commit(); 
        }
        catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
