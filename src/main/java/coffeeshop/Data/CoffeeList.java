package coffeeshop.Data;

import java.util.ArrayList;
import coffeeshop.Entities.Coffee.Coffee;


public class CoffeeList {
    private static ArrayList<Coffee> coffeeList = new ArrayList<Coffee>();

    private static ArrayList<Coffee> genCoffeeList() {
        coffeeList.add(new Coffee("Kona", "Medium", "14.99", 14));
        coffeeList.add(new Coffee("Local", "Dark", "12.99", 16));
        coffeeList.add(new Coffee("Berbers", "Light", "10.99", 14));
        coffeeList.add(new Coffee("Wonka's", "Medium", "9.99", 14));
        coffeeList.add(new Coffee("Smurfs", "Dark", "11.99", 16));
        return coffeeList;
    }

    public static ArrayList<Coffee> getCoffeeList() {
        return genCoffeeList();
    }
}
