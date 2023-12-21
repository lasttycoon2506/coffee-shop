package coffeeshop.Entities.Coffee;

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
    private int coffee_id;
    private String brand;
    private String coffee_name;
    private String roast;
    private float price;
    private String region;
    private int coffee_size;

    public Coffee(){
    }
    public Coffee (String brand, String coffee_name, String roast, float price, String region, int coffee_size) {
        this.brand = brand;
        this.coffee_name = coffee_name;
        this.roast = roast;
        this.price = price;
        this.region = region;
        this.coffee_size = coffee_size;
    }

    public String getBrand(){
        return brand;
    }
    public String getCoffeeName(){
        return coffee_name;
    }
    public String getRoast(){
        return roast;
    }
    public float getPrice(){
        return price;
    }
    public String getRegion(){
        return region;
    }
    public int getCoffeeSize(){
        return coffee_size;
    }
}
    





