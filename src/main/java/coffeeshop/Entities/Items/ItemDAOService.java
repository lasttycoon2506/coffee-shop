package coffeeshop.Entities.Items;



public class ItemDAOService {
     private static ItemDAO itemDAO = new ItemDAO();

    private ItemDAOService(ItemDAO itemDAO) {
        ItemDAOService.itemDAO = itemDAO;
    }

    public static void saveItem(Item item) {
        itemDAO.save(item);
    }
}
