package coffeeshop.Entities.Coffee;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class CoffeeDAO implements DAO<Coffee>{
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private static final EntityManager entityManager = factory.createEntityManager();

    public void save(Coffee coffee) {
        executeInsideTransaction(entityManager -> entityManager.persist(coffee));
    }

    public static boolean coffeeListExistsDB() {
        List<Coffee> coffeeList = entityManager.createQuery("SELECT c FROM Coffee c", Coffee.class).getResultList();
        if (coffeeList.isEmpty()) {
            return false;
        }
        return true;
    }

    public static List<String> getBrands(){
        List<String> brandList =  entityManager.createQuery("SELECT brands.brand FROM Coffee brands", String.class).getResultList();
        return brandList;
    }
    public static List<String> getCoffeeNames(){
        List<String> coffeeNamesList =  entityManager.createQuery("SELECT names.coffee_name FROM Coffee names", String.class).getResultList();
        return coffeeNamesList;
    }
    public static List<String> getRoasts(){
        List<String> roastsList =  entityManager.createQuery("SELECT DISTINCT roasts.roast FROM Coffee roasts", String.class).getResultList();
        return roastsList;
    }
    public static List<Float> getPrices(){
        List<Float> pricesList =  entityManager.createQuery("SELECT prices.price FROM Coffee prices", Float.class).getResultList();
        return pricesList;
    }

    public static List<Coffee> filterByRoast(String roast){
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

    @Override
    public Optional<Coffee> get(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Coffee> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void edit(Coffee t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

    @Override
    public void delete(Coffee t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
