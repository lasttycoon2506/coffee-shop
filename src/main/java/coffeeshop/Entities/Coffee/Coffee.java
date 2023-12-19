package coffeeshop.Entities.Coffee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// @Entity
// @Table(name = "Coffee")
public class Coffee {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coffee_id;
    private final String brand;
    private final String coffee_name;
    private final String roast_type;
    private final Float price;
    private final String region;
    private final Integer coffee_size;

    public Coffee(String brand, String coffee_name, String roast_type, Float price, String region, Integer coffee_size){
        this.brand = brand;
        this.coffee_name = coffee_name;
        this.roast_type = roast_type;
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
    public String getRoastType(){
        return roast_type;
    }
    public Float getPrice(){
        return price;
    }
    public String getRegion(){
        return region;
    }
    public Integer getCoffeeSize(){
        return coffee_size;
    }
}
