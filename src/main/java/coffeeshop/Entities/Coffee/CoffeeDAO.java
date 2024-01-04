package coffeeshop.Entities.Coffee;

import java.math.BigDecimal;
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

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    public Optional<Coffee> get(Integer coffeeId) {
        EntityManager em = getEntityManager();
        return Optional.ofNullable(em.find(Coffee.class, coffeeId));
    }

    public void save(Coffee coffee) {
        executeInsideTransaction(entityManager -> entityManager.persist(coffee));
    }
    
    public static boolean coffeeListExistsDB() {
        EntityManager em = getEntityManager();
        List<Coffee> coffeeList = em.createQuery("SELECT c FROM Coffee c", Coffee.class).getResultList();
        if (coffeeList.isEmpty()) {
            return false;
        }
        return true;
    }

    public static List<String> getBrands(){
        EntityManager em = getEntityManager();
        List<String> brandsList =  em.createQuery("SELECT brands.brand FROM Coffee brands ORDER BY brands.brand", 
                                                            String.class).getResultList();
        return brandsList;
    }
    public static List<BigDecimal> getPrices(){
        EntityManager em = getEntityManager();
        List<BigDecimal> pricesList =  em.createQuery("SELECT DISTINCT prices.price FROM Coffee prices ORDER BY prices.price", 
                                                            BigDecimal.class).getResultList();
        return pricesList;
    }
    public static List<Integer> getSizes(){
        EntityManager em = getEntityManager();
        List<Integer> sizesList =  em.createQuery("SELECT DISTINCT sizes.coffeeSize FROM Coffee sizes ORDER BY sizes.coffeeSize", 
                                                            Integer.class).getResultList();
        return sizesList;
    }

    public static Coffee searchByBrand(String brand){
        EntityManager em = getEntityManager();
        Coffee coffeeItem = em.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE brand = :brand",
                            Coffee.class).setParameter("brand", brand).getSingleResult();
        return coffeeItem;
    }

    public static List<Coffee> filterByRoast(String roast){
        EntityManager em = getEntityManager();
        List<Coffee> filteredRoastList = em.createQuery("SELECT roast FROM Coffee roast WHERE roast.roast = :roastType",
                            Coffee.class).setParameter("roastType", roast).getResultList();
        return filteredRoastList;
    }
    public static List<Coffee> filterByPrice(BigDecimal price){
        EntityManager em = getEntityManager();
        List<Coffee> filteredPriceList = em.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE price = :price",
                            Coffee.class).setParameter("price", price).getResultList();
        return filteredPriceList;
    }
    public static List<Coffee> filterBySize(Integer size){
        EntityManager em = getEntityManager();
        List<Coffee> filteredSizeList = em.createQuery("SELECT size FROM Coffee size WHERE size.coffeeSize = :size",
                            Coffee.class).setParameter("size", size).getResultList();
        return filteredSizeList;
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

    @Override
    public List<Coffee> getAll() {
        return null;
    }

    @Override
    public void edit(Coffee t) {
    }

    @Override
    public void delete(Coffee coffee) {
    }
}
