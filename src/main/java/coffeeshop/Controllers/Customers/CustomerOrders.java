package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import coffeeshop.App;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Orders.OrderDAOService;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
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
	private TableColumn<Order, LocalDate> orderDateColumn;
    @FXML
	private TableColumn<Order, Order> totalItemsColumn; 
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();
    @FXML
    private TableView<Coffee> itemsTable;
    @FXML
	private TableColumn<Coffee, String> brandColumn, roastColumn, priceColumn, sizeColumn;
    private ObservableList<Item> itemsList = FXCollections.observableArrayList();
    private ObservableList<Coffee> coffeeList = FXCollections.observableArrayList();
    private CoffeeDAOService coffeeDAOService = new CoffeeDAOService();
    @FXML
    public void initialize() {
        initOrdersTable();
        initItemsTable();
    }
    

    private void initOrdersTable() {
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        totalItemsColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        totalItemsColumn.setCellFactory(param -> new TableCell<Order, Order>() {
            @Override 
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (empty || order == null) {
                    setText(null);
                }
                else {
                    setText(String.valueOf(order.getTotalItems()));
                    setOnMouseClicked((MouseEvent e) -> {
                        clearTable(itemsTable);
                        Order orderItems = getItem();
                        if (e.getButton() == MouseButton.PRIMARY && orderItems != null) {
                            itemsList.clear();
                            itemsList.addAll(OrderDAOService.getAllItemsForOrder(orderItems.getOrderId()));
                            for (Item item : itemsList) {
                                coffeeList.add(coffeeDAOService.get(item.getCoffeeID()));
                            }
                            loadItemsTable();
                        }
                    });
                }
                }
            }); 
        getAllOrdersForCustomer(customer.getCustomerID());
        loadOrdersTable();
    }

    private void initItemsTable() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeSize"));
        loadItemsTable();
    }
    
    private void getAllOrdersForCustomer(int customerID) {
        ordersList.addAll(OrderDAOService.getAllOrdersForCustomer(customerID));
    }

    private void loadOrdersTable() {
        if (isTableEmpty(ordersTable, ordersList, "NO ORDERS!")) {
        }
        else {
            ordersTable.setItems(ordersList);
        }
    }
    private void loadItemsTable() {
        if (isTableEmpty(itemsTable, coffeeList, "NO ITEMS!")){
        }
        else {
            itemsTable.setItems(coffeeList);
        }
    }

    private boolean isTableEmpty(TableView table, List lst, String msg) {
        if (lst.isEmpty()) {
            table.setPlaceholder(new Label(msg));
            return true;
        }
        return false;
    }

    private void clearTable(TableView table) {
		if (table.getItems().isEmpty()){
			return;
		}
		table.getItems().clear();
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
