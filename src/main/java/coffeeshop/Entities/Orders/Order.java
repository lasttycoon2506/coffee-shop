package coffeeshop.Entities.Orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import coffeeshop.Entities.Items.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "total_items")
    private int totalItems;
    @Column(name = "customer_id")
    private int customerId;
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval=true, mappedBy="order")       //reqd for jpa cascading delete parent to children
    private List<Item> itemsList = new ArrayList<>();

    public Order(){
    }
    public Order (int orderId, LocalDate orderDate, int totalItems, int customerID, List<Item> itemsList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalItems = totalItems;
        this.customerId = customerID;
        this.itemsList = itemsList;
    }
    
    public int getOrderId() {
        return orderId;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public int getTotalItems() {
        return totalItems;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setOrdersDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    public void setCustomerID(int customerID) {
        this.customerId = customerID;
    }
    public void setItemsList(List<Item> itemsList){
        this.itemsList = itemsList;
    }

    public void removeItems(List<Item> itemList) {
        for (Item item : itemList) {
            itemsList.remove(item);
        }
    }
}
