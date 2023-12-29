package coffeeshop.Entities.Coffee;

import java.util.List;

public class CoffeeDAOService {
    private static CoffeeDAO coffeeDAO = new CoffeeDAO();

    private CoffeeDAOService(CoffeeDAO coffeeDAO){
        CoffeeDAOService.coffeeDAO = coffeeDAO;
    }

    public static void saveCoffeeTableToDB(Coffee coffee){
        coffeeDAO.save(coffee);
    }

    public void saveOrder(Coffee coffeeID){
        coffeeDAO.save(coffeeID);
    }

    public static boolean coffeeListExistsDB() {
        return CoffeeDAO.coffeeListExistsDB();
    }

    public static List<String> getBrands() {
        return CoffeeDAO.getBrands();
    }
    public static List<Integer> getPrices() {
        return CoffeeDAO.getPrices();
    }
    public static List<Integer> getSizes() {
        return CoffeeDAO.getSizes();
    }

    public static Coffee searchByBrand(String brand) {
        return CoffeeDAO.searchByBrand(brand);
    }
    public static List<Coffee> filterByPrice(int price) {
        return CoffeeDAO.filterByPrice(price);
    }

    public static List<Coffee> filterByRoast(String roast) {
        return CoffeeDAO.filterByRoast(roast);
    }
    public static List<Coffee> filterBySize(Integer size) {
        return CoffeeDAO.filterBySize(size);
    }
}
