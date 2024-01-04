package coffeeshop.Entities.Items;

import coffeeshop.Entities.Orders.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne                      //req'd by jpa for cascading from parent
    private Order order;

    public Item() {
    }
    public Item(int coffeeId, int quantity, int orderId, Order order) {
        this.coffeeId = coffeeId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.order = order;
    }

    public Integer getItemID() {
        return this.itemId;
    }
    public Integer getCoffeeID() {
        return this.coffeeId;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    public Integer getOrderID() {
        return this.orderId;
    }

    public void setCoffeeID(int coffeeID) {
        this.coffeeId = coffeeID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setOrderID(int orderId) {
        this.orderId = orderId;
    }
    public void setOrder(Order order){
        this.order = order;
    }
}
