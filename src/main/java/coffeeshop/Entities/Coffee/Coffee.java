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
    @Column(name = "coffee_name")
    private String coffeeName;
    private String roast;
    private String price;
    private String region;
    @Column(name = "coffee_size")
    private int coffeeSize;

    public Coffee(){
    }
    public Coffee (String brand, String coffeeName, String roast, String price, String region, int coffeeSize) {
        this.brand = brand;
        this.coffeeName = coffeeName;
        this.roast = roast;
        this.price = price;
        this.region = region;
        this.coffeeSize = coffeeSize;
    }

    public String getBrand(){
        return brand;
    }
    public String getCoffeeName(){
        return coffeeName;
    }
    public String getRoast(){
        return roast;
    }
    public String getPrice(){
        return price;
    }
    public String getRegion(){
        return region;
    }
    public int getCoffeeSize(){
        return coffeeSize;
    }
}
    





