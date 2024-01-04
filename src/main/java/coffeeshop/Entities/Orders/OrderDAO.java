package coffeeshop.Entities.Orders;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import coffeeshop.Entities.Items.Item;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class OrderDAO implements DAO<Order>{
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    
    public Optional<Order> get(Integer orderId) {
        EntityManager em = getEntityManager();
        return Optional.ofNullable(em.find(Order.class, orderId));
    }

    public static int getMostRecentOrder() {
        EntityManager em = getEntityManager();
        Integer orderID = em.createQuery("SELECT MAX(orderId) FROM Order",
                            Integer.class).getSingleResult();
        return orderID;
    }

    public static List<Order> getAllOrdersForCustomer (Integer customerID) {
        EntityManager em = getEntityManager();
        List<Order> customersOrders = em.createQuery("SELECT customersOrders FROM Order customersOrders WHERE customerId = :customerID", 
                                                    Order.class).setParameter("customerID", customerID).getResultList();
        return customersOrders;                                            
    }
    public static List<Item> getAllItemsForOrder (Integer orderID) {
        EntityManager em = getEntityManager();
        List<Item> items = em.createQuery("SELECT items FROM Item items WHERE orderId = :orderID", 
                                                    Item.class).setParameter("orderID", orderID).getResultList();
        return items;                                            
    }

    public void save(Order order) {
        executeInsideTransaction(entityManager -> entityManager.persist(order));
    }
    
    public void edit(Order order) {
        executeInsideTransaction(entityManager -> entityManager.merge(order));
    }
    
    public void delete(Order order) {
        executeInsideTransaction(entityManager -> {
            entityManager.remove(entityManager.contains(order) ? order : entityManager.merge(order));
        });
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
    
    public List<Order> getAll() {
        return null;
    }
}
