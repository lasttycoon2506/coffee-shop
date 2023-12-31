package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.math.BigDecimal;
import coffeeshop.App;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Orders.OrderDAOService;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Entities.Items.Item;
import coffeeshop.Models.Context;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class CustomerOrders {
    private Customer customer = Context.getInstance().getCustomer();
    @FXML
    private TableView<Order> ordersTable;
    @FXML
	private TableColumn<Order, Order> orderDateColumn, totalItemsColumn;
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();
    @FXML
    private TableView<Coffee> itemsTable;
    @FXML
	private TableColumn<Coffee, String> brandColumn, roastColumn;
    @FXML
	private TableColumn<Coffee, BigDecimal> priceColumn;
    @FXML
	private TableColumn<Coffee, Integer> sizeColumn;
    private ObservableList<Coffee> itemsList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        initOrdersTable();
        initItemsTable();
    }
    

    private void initOrdersTable() {
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, Order>("orderDate"));
        totalItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, Order>("totalItems"));
        // orderDateColumn.setCellFactory(param -> new TableCell<Order, Order>() {
        //     @Override 
        //     protected void updateItem(Order item, boolean empty) {
        //         super.updateItem(item, empty);

        //         //Various code to set up the custom CellFactory has been removed.

        //         this.setOnMouseClicked((MouseEvent e) -> {
        //             Order newItem = getItem();
        //             if (e.getButton() == MouseButton.PRIMARY && newItem != null) {
        //                 // Code to set the underlying data item to the new item
        //                 ObservableList<Item> row = (ObservableList<Item>) getTableRow().getItem();
        //                 row.set(columnIndex, newItem);
        //             }
        //         });
        //         }
        //     });
		System.out.println(OrderDAOService.getAllItemsForOrder(2));
        
        getAllOrdersByCustomerID(customer.getCustomerID());
        loadOrdersTable();
    }

    private void initItemsTable() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, BigDecimal>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, Integer>("coffeeSize"));
        loadItemsTable();
    }
    
    private void getAllOrdersByCustomerID(int customerID) {
        ordersList.addAll(OrderDAOService.getAllOrdersForCustomer(customerID));
    }

    private void loadOrdersTable() {
        if (ordersList.isEmpty()) {
            ordersTable.setPlaceholder(new Label("NO ORDERS!"));
        }
        else {
            ordersTable.setItems(ordersList);
        }
    }
    private void loadItemsTable() {
        if (itemsList.isEmpty()) {
            itemsTable.setPlaceholder(new Label("NO ITEMS!"));
        }
        else {
            itemsTable.setItems(itemsList);
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
