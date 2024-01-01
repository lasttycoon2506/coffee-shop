package coffeeshop.Classes;

import java.math.BigDecimal;


public class DisplayItems {
    private int coffeeId;
    private String brand;
    private String roast;
    private BigDecimal price;
    private int coffeeSize;
    private int quantity;
    
    public DisplayItems(int coffeeID, String brand, String roast, BigDecimal price, int coffeeSize, int quantity){
        this.coffeeId = coffeeID;
        this.brand = brand;
        this.roast = roast;
        this.price = price;
        this.coffeeSize = coffeeSize;
        this.quantity = quantity;
    }
}
