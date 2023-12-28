package coffeeshop.Entities.Items;

import jakarta.persistence.Column;
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
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "coffee_id")
    private int coffeeId;
    private int quantity;
    @Column(name = "order_id")
    private int orderId;

    public Item() {
    }
    public Item(int coffeeId, int quantity, int orderId) {
        this.coffeeId = coffeeId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public void setCoffeeID(int coffeeID) {
        this.coffeeId = coffeeID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setOrdersID(int orderId) {
        this.orderId = orderId;
    }
}
