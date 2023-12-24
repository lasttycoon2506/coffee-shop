package coffeeshop.Entities.Coffee;

import java.util.List;

public class CoffeeDAOService {
    private static CoffeeDAO coffeeDAO = new CoffeeDAO();

    private CoffeeDAOService(CoffeeDAO coffeeDAO){
        CoffeeDAOService.coffeeDAO = coffeeDAO;
    }
    
    public static void saveCoffee(Coffee coffee) {
        coffeeDAO.save(coffee);
    }

    public void saveOrder(){

    }

    public static boolean coffeeListExistsDB() {
        return CoffeeDAO.coffeeListExistsDB();
    }

    public static List<Coffee> getCoffeeList() {
        return CoffeeDAO.getCoffeeList();
    }

    public static List<Coffee> getbyRoastList(String roast) {
        return CoffeeDAO.getByRoastList(roast);
    }
}
