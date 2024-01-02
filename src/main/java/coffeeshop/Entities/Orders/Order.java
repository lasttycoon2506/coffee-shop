package coffeeshop.Entities.Orders;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Order(){
    }
    public Order (int orderId, LocalDate orderDate, int totalItems, int customerID) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalItems = totalItems;
        this.customerId = customerID;
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
}
