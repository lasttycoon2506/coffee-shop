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
    public static List<String> getNames() {
        return CoffeeDAO.getNames();
    }
    public static List<Float> getPrices() {
        return CoffeeDAO.getPrices();
    }
    public static List<String> getRegions() {
        return CoffeeDAO.getRegions();
    }
    public static List<Integer> getSizes() {
        return CoffeeDAO.getSizes();
    }

    public static Coffee searchByBrand(String brand) {
        return CoffeeDAO.searchByBrand(brand);
    }
    public static Coffee searchByName(String name) {
        return CoffeeDAO.searchByName(name);
    }

    public static List<Coffee> filterByRoast(String roast) {
        return CoffeeDAO.filterByRoast(roast);
    }
}
