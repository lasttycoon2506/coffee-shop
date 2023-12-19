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
    private String brand;
    private String coffee_name;
    private String roast_type;
    private Float price;
    private String region;
    private Integer coffee_size;

    public Coffee(){
    }

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
