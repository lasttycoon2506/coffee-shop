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
    @Column(name = "orders_id")
    private int orderId;
    @Column(name = "orders_date")
    private LocalDate orderDate;
    @Column(name = "total_items")
    private int totalItems;
    @Column(name = "customer_id")
    private int customerId;

    public Order(){
    }
    public Order (int orderId, LocalDate orderDate, int numberOfItems, int customerID) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalItems = numberOfItems;
        this.customerId = customerID;
    }

    public void setOrdersDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public void setNumberOfItems(int numberOfItems) {
        this.totalItems = numberOfItems;
    }
    public void setCustomerID(int customerID) {
        this.customerId = customerID;
    }
}
