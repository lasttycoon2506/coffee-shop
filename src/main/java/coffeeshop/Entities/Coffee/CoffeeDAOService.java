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

    public static List<String> getBrands() {
        return CoffeeDAO.getBrands();
    }
    public static List<String> getCoffeeNames() {
        return CoffeeDAO.getCoffeeNames();
    }
    public static List<String> getRoasts() {
        return CoffeeDAO.getRoasts();
    }

    public static List<Coffee> filterByRoast(String roast) {
        return CoffeeDAO.filterByRoast(roast);
    }
}
