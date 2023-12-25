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
        List<String> brandList =  entityManager.createQuery("SELECT brands.brand FROM Coffee brands ORDER BY brands.brand", 
                                                            String.class).getResultList();
        return brandList;
    }
    public static List<String> getNames(){
        List<String> namesList =  entityManager.createQuery("SELECT names.coffee_name FROM Coffee names ORDER BY names.coffee_name",
                                                                 String.class).getResultList();
        return namesList;
    }
    public static List<Float> getPrices(){
        List<Float> pricesList =  entityManager.createQuery("SELECT prices.price FROM Coffee prices ORDER BY prices.price", 
                                                            Float.class).getResultList();
        return pricesList;
    }
    public static List<String> getRegions(){
        List<String> regionsList =  entityManager.createQuery("SELECT regions.region FROM Coffee regions ORDER BY regions.region", 
                                                            String.class).getResultList();
        return regionsList;
    }
    public static List<Integer> getSizes(){
        List<Integer> sizesList =  entityManager.createQuery("SELECT DISTINCT sizes.coffee_size FROM Coffee sizes ORDER BY sizes.coffee_size", 
                                                            Integer.class).getResultList();
        return sizesList;
    }

    public static Coffee searchByBrand(String brand){
        Coffee coffeeItem = entityManager.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE brand = :brand",
                            Coffee.class).setParameter("brand", brand).getSingleResult();
        return coffeeItem;
    }
    public static Coffee searchByName(String name){
        Coffee coffeeItem = entityManager.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE coffee_name = :name",
                            Coffee.class).setParameter("name", name).getSingleResult();
        return coffeeItem;
    }
    public static Coffee searchByPrice(Float price){
        Coffee coffeeItem = entityManager.createQuery("SELECT coffeeItem FROM Coffee coffeeItem WHERE price = :price",
                            Coffee.class).setParameter("price", price).getSingleResult();
        return coffeeItem;
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
