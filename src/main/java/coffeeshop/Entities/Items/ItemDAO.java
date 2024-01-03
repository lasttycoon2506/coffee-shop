package coffeeshop.Entities.Items;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class ItemDAO implements DAO<Item>{
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private static final EntityManager entityManager = factory.createEntityManager();

    public Optional<Item> get(Integer itemId) {
        return Optional.ofNullable(entityManager.find(Item.class, itemId));
    }

    public List<Item> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    public void save(Item item) {
        executeInsideTransaction(entityManager -> entityManager.persist(item));
    }

    public void edit(Item t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

    public void delete(Item item) {
        executeInsideTransaction(entityManager -> {
            entityManager.remove(entityManager.contains(item) ? item : entityManager.merge(item));  
        });
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
