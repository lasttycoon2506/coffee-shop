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
    @Column(name = "items_id")
    private int itemsId;
    @Column(name = "coffee_id")
    private int coffeeId;
    private int quantity;
    @Column(name = "orders_id")
    private int ordersId;

    public Item() {
    }
    public Item(int coffeeID, int quantity, int ordersID) {
        this.coffeeId = coffeeID;
        this.quantity = quantity;
        this.ordersId = ordersID;
    }

    public void setCoffeeID(int coffeeID) {
        this.coffeeId = coffeeID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setOrdersID(int ordersID) {
        this.ordersId = ordersID;
    }
}
