package coffeeshop.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import coffeeshop.Entities.Coffee.Coffee;


public class CoffeeList {
    private static ArrayList<Coffee> coffeeList = new ArrayList<Coffee>();

    private static ArrayList<Coffee> genCoffeeList() {
        coffeeList.add(new Coffee("Kona", "Medium", castBigDecimal("11.99"), 14));
        coffeeList.add(new Coffee("Local", "Dark", castBigDecimal("12.99"), 16));
        coffeeList.add(new Coffee("Berbers", "Light", castBigDecimal("10.99"), 14));
        coffeeList.add(new Coffee("Wonka's", "Medium", castBigDecimal("10.99"), 14));
        coffeeList.add(new Coffee("Smurfs", "Dark", castBigDecimal("11.99"), 16));
        coffeeList.add(new Coffee("Willy's", "Dark", castBigDecimal("9.99"), 18));
        coffeeList.add(new Coffee("Rocky Road", "Light", castBigDecimal("11.99"), 18));
        coffeeList.add(new Coffee("Surfs Up", "Medium", castBigDecimal("11.99"), 18));
        coffeeList.add(new Coffee("Z", "Dark", castBigDecimal("9.99"), 12));
        coffeeList.add(new Coffee("Everyday", "Light", castBigDecimal("12.99"), 12));
        return coffeeList;
    }

    private static BigDecimal castBigDecimal(String string) {
        return null;
    }

    public static ArrayList<Coffee> getCoffeeList() {
        return genCoffeeList();
    }
}
