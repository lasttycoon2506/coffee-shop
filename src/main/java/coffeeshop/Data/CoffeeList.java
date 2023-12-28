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
        coffeeList.add(new Coffee("Willy's", "Dark", "9.99", 18));
        coffeeList.add(new Coffee("Rocky Road", "Light", "11.99", 18));
        coffeeList.add(new Coffee("Surfs Up", "Medium", "13.99", 18));
        coffeeList.add(new Coffee("Z", "Dark", "8.99", 12));
        coffeeList.add(new Coffee("Everyday", "Light", "12.99", 12));
        return coffeeList;
    }

    public static ArrayList<Coffee> getCoffeeList() {
        return genCoffeeList();
    }
}
