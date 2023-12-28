package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import coffeeshop.App;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Models.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class CustomerOrders {
    private Customer customer = Context.getInstance().getCustomer();
    private TableView<Order> ordersTable;
    @FXML
	private TableColumn<Order, LocalDate> orderDateColumn;
    @FXML
	private TableColumn<Order, Integer> totalItemsColumn;
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();
    public void initialize() {
		ordersTable.setPlaceholder(new Label("NO ORDERS!"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        totalItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("totalItems"));
        System.out.println(customer.getCustomerID());
    }

	
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}
