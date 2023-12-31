package coffeeshop.Entities.Coffee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CoffeeDAOService {
    private static CoffeeDAO coffeeDAO = new CoffeeDAO();

    private CoffeeDAOService(CoffeeDAO coffeeDAO){
        CoffeeDAOService.coffeeDAO = coffeeDAO;
    }

    public Optional<Coffee> get(Integer coffeeID) {
        return coffeeDAO.get(coffeeID);
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
    public static List<BigDecimal> getPrices() {
        return CoffeeDAO.getPrices();
    }
    public static List<Integer> getSizes() {
        return CoffeeDAO.getSizes();
    }

    public static Coffee searchByBrand(String brand) {
        return CoffeeDAO.searchByBrand(brand);
    }

    public static List<Coffee> filterByRoast(String roast) {
        return CoffeeDAO.filterByRoast(roast);
    }
    public static List<Coffee> filterByPrice(BigDecimal price) {
        return CoffeeDAO.filterByPrice(price);
    }
    public static List<Coffee> filterBySize(Integer size) {
        return CoffeeDAO.filterBySize(size);
    }
}
