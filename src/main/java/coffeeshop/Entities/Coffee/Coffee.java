package coffeeshop.Entities.Coffee;

import java.math.BigDecimal;
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
    private final String brand;
    private final String roast;
    private final BigDecimal price;
    @Column(name = "coffee_size")
    private final int coffeeSize;

    public Coffee (String brand, String roast, BigDecimal price, int coffeeSize) {
        this.brand = brand;
        this.roast = roast;
        this.price = price;
        this.coffeeSize = coffeeSize;
    }

    public int getCoffeeID() {
        return coffeeId;
    } 
    public String getBrand(){
        return brand;
    }
    public String getRoast(){
        return roast;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public int getCoffeeSize(){
        return coffeeSize;
    }
}
    





