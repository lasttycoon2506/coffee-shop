package coffeeshop.Entities.Items;


public class ItemDAOService {
     private static ItemDAO itemDAO = new ItemDAO();

    public static void saveItem(Item item) {
        itemDAO.save(item);
    }

    public static void deleteItem(Item item) {
        itemDAO.delete(item);
    }
}
