package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.time.LocalDate;
import coffeeshop.App;
import coffeeshop.Entities.Orders.Order;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CustomerOrders {
    private TableView<Order> ordersTable;
    @FXML
	private TableColumn<Order, LocalDate> orderDateColumn;
    @FXML
	private TableColumn<Order, Integer> totalItemsColumn;

	
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}
