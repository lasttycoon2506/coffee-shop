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
        List<String> brandsList =  entityManager.createQuery("SELECT brands.brand FROM Coffee brands ORDER BY brands.brand", 
                                                            String.class).getResultList();
        return brandsList;
    }
    public static List<BigDecimal> getPrices(){
        List<BigDecimal> pricesList =  entityManager.createQuery("SELECT DISTINCT prices.price FROM Coffee prices ORDER BY prices.price", 
                                                            BigDecimal.class).getResultList();
        return pricesList;
    }
    public static List<Integer> getSizes(){
        List<Integer> sizesList =  entityManager.createQuery("SELECT DISTINCT sizes.coffeeSize FROM Coffee sizes ORDER BY sizes.coffeeSize", 
                                                            Integer.class).getResultList();
        return sizesList;
    }

    public static Coffee searchByBrand(String brand){
        Coffee coffeeItem = entityManager.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE brand = :brand",
                            Coffee.class).setParameter("brand", brand).getSingleResult();
        return coffeeItem;
    }

    public static List<Coffee> filterByRoast(String roast){
        List<Coffee> filteredRoastList = entityManager.createQuery("SELECT roast FROM Coffee roast WHERE roast.roast = :roastType",
                            Coffee.class).setParameter("roastType", roast).getResultList();
        return filteredRoastList;
    }
    public static List<Coffee> filterByPrice(BigDecimal price){
        List<Coffee> filteredPriceList = entityManager.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE price = :price",
                            Coffee.class).setParameter("price", price).getResultList();
        return filteredPriceList;
    }
    public static List<Coffee> filterBySize(int size){
        List<Coffee> filteredSizeList = entityManager.createQuery("SELECT size FROM Coffee size WHERE size.coffeeSize = :size",
                            Coffee.class).setParameter("size", size).getResultList();
        return filteredSizeList;
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
