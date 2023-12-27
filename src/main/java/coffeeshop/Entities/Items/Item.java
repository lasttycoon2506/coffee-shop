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
    private int quantity;
    private int coffee_id;
    private int orders_id;

    private Item() {
    }

    private Item(int quantity, int coffeeID, int ordersID) {
        this.quantity = quantity;
        this.coffee_id = coffeeID;
        this.orders_id = ordersID;
    }

}
