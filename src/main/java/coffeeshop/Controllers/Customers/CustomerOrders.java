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
import coffeeshop.Entities.Items.ItemDAOService;
import coffeeshop.Models.Context;
import coffeeshop.Models.DisplayItems;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private TableColumn<Order, Order> totalItemsColumn, deleteColumnOrders; 
    private ObservableList<Order> ordersList = FXCollections.observableArrayList();
    @FXML
    private TableView<DisplayItems> itemsTable;
    @FXML
	private TableColumn<DisplayItems, String> brandColumn, roastColumn, priceColumn, sizeColumn, quantityColumn;
    @FXML
    private TableColumn<DisplayItems, DisplayItems> deleteColumnItems;
    private ObservableList<Item> itemsList = FXCollections.observableArrayList();
    private ObservableList<DisplayItems> displayItemsList = FXCollections.observableArrayList();
    private CoffeeDAOService coffeeDAOService = new CoffeeDAOService();
    @FXML
    public void initialize() {
        initOrdersTable();
        initItemsTable();
    }
    

    private void initOrdersTable() {
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        totalItemsColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
        totalItemsColumn.setStyle("-fx-alignment: CENTER;");
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
                                displayItemsList.add(new DisplayItems(coffeeDAOService.get(item.getCoffeeID()), item));
                            }
                            loadItemsTable();
                        }
                    });
                }
                }
            }); 
        deleteColumnOrders.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
		deleteColumnOrders.setStyle("-fx-alignment: CENTER;");
        deleteColumnOrders.setCellFactory(column -> new TableCell<Order, Order>() {
			private final Button deleteButton = new Button("X");
			@Override
			protected void updateItem(Order order, boolean empty) {
				super.updateItem(order, empty);
				if (order == null) {
					setGraphic(null);
					return;
				}
				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> {
	                                            getTableView().getItems().remove(order);
                                                List<Item> itemsToDelete = OrderDAOService.getAllItemsForOrder(getTableRow().getItem().getOrderId());
                                                OrderDAOService.getOrder(getTableRow().getItem().getOrderId()).removeItems(itemsToDelete);
                                                OrderDAOService.deleteOrder(OrderDAOService.getOrder(getTableRow().getItem().getOrderId()));
                                                });
			}
		});
        getAllOrdersForCustomer(customer.getCustomerID());
        loadOrdersTable();
    }


    private void initItemsTable() {
        brandColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getCoffee().getBrand()));
        roastColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getCoffee().getRoast()));
        priceColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getCoffee().getPrice())));
        sizeColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getCoffee().getCoffeeSize())));
        quantityColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getItem().getQuantity())));
        deleteColumnItems.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
		deleteColumnItems.setStyle("-fx-alignment: CENTER;");
        deleteColumnItems.setCellFactory(column -> new TableCell<DisplayItems, DisplayItems>() {
			private final Button deleteButton = new Button("X");
			@Override
			protected void updateItem(DisplayItems displayItem, boolean empty) {
				super.updateItem(displayItem, empty);
				if (displayItem == null) {
					setGraphic(null);
					return;
				}
				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> {
                    clearTable(ordersTable);
					getTableView().getItems().remove(displayItem);
                    Integer quantityToEdit = getTableRow().getItem().getItem().getQuantity();  
                    Order orderToEdit = OrderDAOService.getOrder(getTableRow().getItem().getItem().getOrderID());
                    orderToEdit.setTotalItems(orderToEdit.getTotalItems() - quantityToEdit);
                    if (orderToEdit.getTotalItems() == 0){
                        ItemDAOService.deleteItem(getTableRow().getItem().getItem());
                        OrderDAOService.deleteOrder(orderToEdit);
                    }
                    else {
                        OrderDAOService.editOrder(orderToEdit);
                        ItemDAOService.deleteItem(getTableRow().getItem().getItem());
                    }
                    refreshOrdersTable(customer.getCustomerID());
                }
				);
			}
		});
        loadItemsTable();
    }

    private void refreshOrdersTable(int customerID){
        ordersList.addAll(OrderDAOService.getAllOrdersForCustomer(customerID));
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
        if (isTableEmpty(itemsTable, displayItemsList, "NO ITEMS!")){
        }
        else {
            itemsTable.setItems(displayItemsList);
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
