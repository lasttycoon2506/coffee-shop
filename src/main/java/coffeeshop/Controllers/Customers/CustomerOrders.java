package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import coffeeshop.App;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Orders.OrderDAOService;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Models.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        totalItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("totalItems"));
        // ordersTable.setPlaceholder(new Label("NO ORDERS!"));
        getAllOrdersForCustomer(customer.getCustomerID());
        loadOrdersTable();
    }


    private List<Order> getAllOrdersForCustomer(int customerID) {
        ordersList.addAll(OrderDAOService.getAllOrdersForCustomer(customerID));
        return ordersList;
    }

    private void loadOrdersTable() {
        ordersTable.setItems(ordersList);
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
