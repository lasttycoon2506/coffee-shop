package coffeeshop.Entities.Orders;

import java.time.LocalDate;
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
    private int orders_id;
    private LocalDate orders_date;
    private int number_of_total_items;
    private int customer_id;

    public Order(){
    }
    public Order (int ordersID, LocalDate ordersDate, int numberOfItems, int customerID) {
        this.orders_id = ordersID;
        this.orders_date = ordersDate;
        this.number_of_total_items = numberOfItems;
        this.customer_id = customerID;
    }

    public void setOrdersDate(LocalDate ordersDate) {
        this.orders_date = ordersDate;
    }
    public void setNumberOfItems(int numberOfItems) {
        this.number_of_total_items = numberOfItems;
    }
    public void setCustomerID(int customerID) {
        this.customer_id = customerID;
    }
}
