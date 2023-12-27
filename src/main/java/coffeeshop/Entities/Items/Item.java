package coffeeshop.Entities.Items;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int items_id;
    private int coffee_id;
    private int quantity;
    private int orders_id;

    public Item() {
    }
    public Item(int coffeeID, int quantity, int ordersID) {
        this.coffee_id = coffeeID;
        this.quantity = quantity;
        this.orders_id = ordersID;
    }

    public void setCoffeeID(int coffeeID) {
        this.coffee_id = coffeeID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setOrdersID(int ordersID) {
        this.orders_id = ordersID;
    }
}
