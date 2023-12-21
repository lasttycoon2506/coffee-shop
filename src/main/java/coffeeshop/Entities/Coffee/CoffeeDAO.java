package coffeeshop.Entities.Coffee;

import java.util.List;
import java.util.function.Consumer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class CoffeeDAO {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private static final EntityManager entityManager = factory.createEntityManager();

    public static void save(Coffee coffee) {
        executeInsideTransaction(entityManager -> entityManager.persist(coffee));
    }

    public static boolean coffeeListExistsDB() {
        List<String> dbCoffeeList = entityManager.createQuery("SELECT c from Coffee c").getResultList();
        if (dbCoffeeList.isEmpty()) {
            return false;
        }
        return true;
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
