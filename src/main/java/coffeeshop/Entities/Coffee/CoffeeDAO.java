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
        List<Coffee> coffeeList = entityManager.createQuery("SELECT c from Coffee c", Coffee.class).getResultList();
        if (coffeeList.isEmpty()) {
            return false;
        }
        return true;
    }

    public static List<Coffee> getCoffeeList(){
        List<Coffee> coffeeList =  entityManager.createQuery("SELECT c from Coffee c", Coffee.class).getResultList();
        return coffeeList;
    }

    public static List<Coffee> getByRoastList(String roast){
        List<Coffee> roastList = entityManager.createQuery("SELECT roast from Coffee roast WHERE roast.roast = :roastType",
                            Coffee.class).setParameter("roastType", roast).getResultList();
        return roastList;
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
