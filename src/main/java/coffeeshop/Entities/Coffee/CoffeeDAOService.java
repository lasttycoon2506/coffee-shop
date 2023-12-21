package coffeeshop.Entities.Coffee;


public class CoffeeDAOService {
    
    public static void saveCoffee(Coffee coffee) {
        CoffeeDAO.save(coffee);
    }

    public static boolean coffeeListExistsDB() {
        return CoffeeDAO.coffeeListExistsDB();
    }
}
