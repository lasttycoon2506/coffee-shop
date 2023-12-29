package coffeeshop.Entities.Coffee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_id")
    private int coffeeId;
    private String brand;
    private String roast;
    private float price;
    @Column(name = "coffee_size")
    private int coffeeSize;

    public Coffee(){
    }
    public Coffee (String brand, String roast, float price, int coffeeSize) {
        this.brand = brand;
        this.roast = roast;
        this.price = price;
        this.coffeeSize = coffeeSize;
    }

    public String getBrand(){
        return brand;
    }
    public String getRoast(){
        return roast;
    }
    public float getPrice(){
        return price;
    }
    public int getCoffeeSize(){
        return coffeeSize;
    }
}
    





