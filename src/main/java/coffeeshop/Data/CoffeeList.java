package coffeeshop.Data;

import java.util.ArrayList;
import coffeeshop.Entities.Coffee.Coffee;


public class CoffeeList {
    private static ArrayList<Coffee> coffeeList = new ArrayList<Coffee>();

    private static ArrayList<Coffee> genCoffeeList() {
        coffeeList.add(new Coffee("Kona", "Kona's Best", "Medium", 14.99f, "Ethiopia", 12));
        coffeeList.add(new Coffee("Local", "Localie", "Dark", 12.99f, "Peru", 16));
        coffeeList.add(new Coffee("Berbers", "Get Roasted", "Light", 10.99f, "Indonesia", 14));
        coffeeList.add(new Coffee("Wonka's", "Sweet Roast", "Medium", 9.99f, "Amazon", 14));
        coffeeList.add(new Coffee("Smurfs", "Smurfs Village ", "Dark", 11.99f, "Yunnan", 18));
        return coffeeList;
    }

    public static ArrayList<Coffee> getCoffeeList() {
        return genCoffeeList();
    }
}
