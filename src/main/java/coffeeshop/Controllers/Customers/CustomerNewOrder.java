package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import coffeeshop.App;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Entities.Items.Item;
import coffeeshop.Entities.Items.ItemDAOService;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Orders.OrderDAOService;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;


public class CustomerNewOrder {
	private Customer customer = Context.getInstance().getCustomer();
	private Order newOrder = new Order();
	private HashMap<Integer, Integer> coffeeQuantityHash = new HashMap<>();
	private List<Item> newItemsList = new ArrayList<Item>();
	@FXML
    private TableView<Coffee> orderTable, filterTable;
	@FXML
	private ComboBox<String> brandBox, roastBox;
	@FXML
	private ComboBox<BigDecimal> priceBox;
	@FXML
	private ComboBox<Integer> sizeBox;
	@FXML
	private Label timeDisplay;
	@FXML
	private ObservableList<Coffee> orderList = FXCollections.observableArrayList();
	private ObservableList<Coffee> filteredList = FXCollections.observableArrayList();
	private static final List<String> brandsList = CoffeeDAOService.getBrands();
	private static final List<BigDecimal> pricesList = CoffeeDAOService.getPrices();
	private static final List<Integer> sizeList = CoffeeDAOService.getSizes();
	@FXML
	private TableColumn<Coffee, String> brandColumn, roastColumn;
	@FXML
	private TableColumn<Coffee, BigDecimal> priceColumn;
	@FXML
	private TableColumn<Coffee, Integer> sizeColumn;
	@FXML
	private TableColumn<Coffee, Coffee> deleteColumn;
	@FXML
	private TableColumn<Coffee, String> brandColumnFilterTable, roastColumnFilterTable;
	@FXML
	private TableColumn<Coffee, BigDecimal> priceColumnFilterTable;
	@FXML
	private TableColumn<Coffee, Integer> sizeColumnFilterTable;
	@FXML
	private TableColumn<Coffee, Coffee> addColumnFilterTable;
	private LocalDate currentDate;
	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			timeDisplay.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd      HH:mm:ss")));
			currentDate = LocalDate.now();
		}
	};
	public void initialize() {
		timer.start();
		orderTable.setPlaceholder(new Label("CURRENT ORDER"));
		filterTable.setPlaceholder(new Label("FILTERED RESULTS"));
		initOrderTable();	
		initFilterTable();		
		fillComboBoxes();	
    }


	private void initOrderTable() {
		brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, BigDecimal>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, Integer>("coffeeSize"));	
		deleteColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
		deleteColumn.setStyle("-fx-alignment: CENTER;");
		deleteColumn.setCellFactory(column -> new TableCell<Coffee, Coffee>() {
			private final Button deleteButton = new Button("X");
			@Override
			protected void updateItem(Coffee coffee, boolean empty) {
				super.updateItem(coffee, empty);
				if (coffee == null) {
					setGraphic(null);
					return;
				}
				setGraphic(deleteButton);
				deleteButton.setOnAction(
					event -> getTableView().getItems().remove(coffee)
				);
			}
		});
	}
	

	private void initFilterTable() {
		brandColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        roastColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
		priceColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, BigDecimal>("price"));
        sizeColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, Integer>("coffeeSize"));	
		addColumnFilterTable.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		addColumnFilterTable.setStyle("-fx-alignment: CENTER;");
		addColumnFilterTable.setCellFactory(param -> new TableCell<Coffee, Coffee>() {
			private final Button addButton = new Button("ADD");
			@Override
			protected void updateItem(Coffee coffee, boolean empty) {
				super.updateItem(coffee, empty);
				if (coffee == null) {
					setGraphic(null);
					return;
				}
				setGraphic(addButton);
				addButton.setOnAction(
					event -> orderList.add(coffee)
				);
				loadOrderTable();
			}
		});
	}
	
	//sets combo boxes w/ chooseable properties
	private void fillComboBoxes() {
		brandBox.getItems().addAll(brandsList);
		roastBox.getItems().addAll("Light", "Medium", "Dark");
		priceBox.getItems().addAll(pricesList);
		sizeBox.getItems().addAll(sizeList);
	}

	@FXML
	private void order() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException   {
		if (orderList.isEmpty()) {
			notificationWindow("error", "Empty Order!");
		}
		else {
			//first saves order object to Orders table
			newOrder.setOrdersDate(currentDate);
			newOrder.setTotalItems(orderList.size());
			newOrder.setCustomerID(customer.getCustomerID());
			OrderDAOService.saveOrder(newOrder);
			switchToCustomerPg();
			notificationWindow("confirmation", null);
			//each coffee item stored in hash as coffeeID and quantity
			for (Coffee coffee: orderList){
				if (coffeeQuantityHash.get(coffee.getCoffeeID()) == null) {
					coffeeQuantityHash.put(coffee.getCoffeeID(), 1);
				}
				else {
					coffeeQuantityHash.put(coffee.getCoffeeID(), coffeeQuantityHash.get(coffee.getCoffeeID()) + 1);
				}
			}
			//loops through coffeehash adding item object for each quantity of coffeeID
			coffeeQuantityHash.forEach((coffeeID, quantity) -> newItemsList.add(new Item(coffeeID, quantity, 
																						OrderDAOService.getMostRecentOrder(), newOrder)));
			for (Item newItem : newItemsList) {
				ItemDAOService.saveItem(newItem);
			}
			newOrder.setItemsList(newItemsList);
		}
	}
	
	//choicebox selected by property displayed in orders table
	@FXML
	private void selectedByBrand(){
		Coffee coffeeItem = CoffeeDAOService.searchByBrand(brandBox.getValue());
		orderList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(brandBox);
	}
	@FXML
	private void filterByRoast(){
		clearFilterTable();
		List<Coffee> listByRoast = CoffeeDAOService.filterByRoast(roastBox.getValue());
		filteredList.addAll(listByRoast);
		loadFilterTable();
	}
	@FXML
	private void filterByPrice(){
		clearFilterTable();
		List<Coffee> listbyPrice = CoffeeDAOService.filterByPrice(priceBox.getValue());
		filteredList.addAll(listbyPrice);
		loadFilterTable();
	}
	@FXML
	private void filterBySize(){
		clearFilterTable();
		List<Coffee> listBySize = CoffeeDAOService.filterBySize(sizeBox.getValue());
		filteredList.addAll(listBySize);
		loadFilterTable();
	}
	
	private void resetChoiceBox(ComboBox<String> comboBox){
		comboBox.setValue(null);
		comboBox.setButtonCell(new ListCell<>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(comboBox.getPromptText());
            } else {
                setText(item);
            }
        }
    });
	}

	private void loadOrderTable()  {
        orderTable.setItems(orderList);
	}
	private void loadFilterTable()  {
        filterTable.setItems(filteredList);
	}
	private void clearFilterTable() {
		if (filterTable.getItems().isEmpty()){
			return;
		}
		filterTable.getItems().clear();
	}

    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

	private static void notificationWindow(String windowType, String message) {
        Alert dialog;
        if (windowType.equals("confirmation")) {
            dialog = new Alert(AlertType.CONFIRMATION, "Your Order Has Been Placed!", ButtonType.OK);
        }
        else {
            dialog = new Alert(AlertType.ERROR, message, ButtonType.OK);
        }
        dialog.show();
    }
}

