package coffeeshop.Entities.Coffee;

import java.util.List;

public class CoffeeDAOService {
    
    public static void saveCoffee(Coffee coffee) {
        CoffeeDAO.save(coffee);
    }

    public static boolean coffeeListExistsDB() {
        return CoffeeDAO.coffeeListExistsDB();
    }

    public static List<Coffee> getCoffeeList() {
        return CoffeeDAO.getCoffeeList();
    }
}
