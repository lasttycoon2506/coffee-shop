package coffeeshop.Controllers.Customers;

import java.io.IOException;
import coffeeshop.App;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Orders.OrderDAOService;
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
    @FXML
    private TableView<Order> ordersTable;
    @FXML
	private TableColumn<Order, String> orderDateColumn;
    @FXML
	private TableColumn<Order, String> totalItemsColumn;
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
        totalItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("totalItems"));
        getAllOrdersByCustomerID(customer.getCustomerID());
        loadOrdersTable();
    }
    
    @FXML
    private void getAllOrdersByCustomerID(int customerID) {
        ordersList.addAll(OrderDAOService.getAllOrdersByCustomerID(customerID));
    }

    @FXML
    private void loadOrdersTable() {
        if (ordersList.isEmpty()) {
            ordersTable.setPlaceholder(new Label("NO ORDERS!"));
        }
        else {
            ordersTable.setItems(ordersList);
        }
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
