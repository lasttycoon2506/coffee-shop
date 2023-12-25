package coffeeshop.Entities.Customers;

import java.lang.reflect.Field;
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
    private static final EntityManager entityManager = factory.createEntityManager();
    
    @Override
    public Optional<Customer> get(Integer id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    public List<Customer> getAll(){
        return null;
    }

    public static boolean userNameExists(String userName)  {
        try {
            executeInsideTransaction(entityManager -> 
                                    entityManager.createQuery("SELECT user FROM Customer user WHERE user.user_name = :userName", 
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
        Customer customer = entityManager.createQuery("SELECT pwHash FROM Customer pwHash WHERE user_name = :userName",
                            Customer.class).setParameter("userName", user).getSingleResult();
        // gets pword field from customer obj
        Field pWordHash = customer.getClass().getDeclaredField("pword");
        pWordHash.setAccessible(true);
        BCrypt.Result result = BCrypt.verifyer().verify(pw.toCharArray(), pWordHash.get(customer).toString().trim());
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
