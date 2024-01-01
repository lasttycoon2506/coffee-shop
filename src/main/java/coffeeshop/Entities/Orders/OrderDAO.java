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
    private static final EntityManager entityManager = factory.createEntityManager();
    
    public Optional<Order> get(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    
    public List<Order> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    public static int getMostRecentOrder() {
        Integer orderID = entityManager.createQuery("SELECT MAX(orderId) FROM Order",
                            Integer.class).getSingleResult();
        return orderID;
    }

    public static List<Order> getAllOrdersForCustomer (Integer customerID) {
        List<Order> customersOrders = entityManager.createQuery("SELECT customersOrders FROM Order customersOrders WHERE customerId = :customerID", 
                                                    Order.class).setParameter("customerID", customerID).getResultList();
        return customersOrders;                                            
    }
    public static List<Item> getAllItemsForOrder (Integer orderID) {
        List<Item> items = entityManager.createQuery("SELECT items FROM Item items WHERE orderId = :orderID", 
                                                    Item.class).setParameter("orderID", orderID).getResultList();
        return items;                                            
    }

    public void save(Order order) {
        executeInsideTransaction(entityManager -> entityManager.persist(order));
    }
    
    public void edit (Order t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }
    
    public void delete(Order t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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
