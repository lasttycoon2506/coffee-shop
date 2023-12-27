package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import coffeeshop.App;
import coffeeshop.Data.CoffeeList;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Entities.Orders.Order;
import coffeeshop.Entities.Orders.OrderDAOService;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;


public class CustomerOrders {
	private Customer customer = Context.getInstance().getCustomer();
	private Order newOrder = new Order();
	@FXML
    private TableView<Coffee> orderTable, filterTable;
	@FXML
	private ComboBox<String> brandBox, nameBox, roastBox, priceBox, regionBox;
	@FXML
	private ComboBox<Integer> sizeBox;
	@FXML
	private Label timeDisplay;
	@FXML
	private final ObservableList<Coffee> orderList = FXCollections.observableArrayList();
	private final ObservableList<Coffee> filteredList = FXCollections.observableArrayList();
	private static final List<String> brandsList = CoffeeDAOService.getBrands();
	private static final List<String> namesList = CoffeeDAOService.getNames();
	private static final List<String> pricesList = CoffeeDAOService.getPrices();
	private static final List<String> regionsList = CoffeeDAOService.getRegions();
	private static final List<Integer> sizeList = CoffeeDAOService.getSizes();
	@FXML
	private TableColumn<Coffee, String> brandColumn, nameColumn, roastColumn, priceColumn, regionColumn, sizeColumn;
	@FXML
	private TableColumn<Coffee, Coffee> deleteColumn;
	@FXML
	private TableColumn<Coffee, String> brandColumnFilterTable, nameColumnFilterTable, roastColumnFilterTable, priceColumnFilterTable,
										regionColumnFilterTable, sizeColumnFilterTable;
	@FXML
	private TableColumn<Coffee, Coffee> addColumnFilterTable;
	public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
		timer.start();
		initOrderTable();	
		initFilterTable();		
		saveCoffeeToDB();
		fillComboBoxes();	
    }


	private void initOrderTable() {
		brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeName"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("price"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("region"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeSize"));
		deleteColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(column.getValue()));
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
        nameColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeName"));
        roastColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
		priceColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("price"));
        regionColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("region"));
        sizeColumnFilterTable.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeSize"));	
		addColumnFilterTable.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
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
		nameBox.getItems().addAll(namesList);
		roastBox.getItems().addAll("Light", "Medium", "Dark");
		priceBox.getItems().addAll(pricesList);
		regionBox.getItems().addAll(regionsList);
		sizeBox.getItems().addAll(sizeList);
	}

	@FXML
	private void addToOrder() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException   {
		for (Coffee coffee: orderList){
			Field coffeeID = coffee.getClass().getDeclaredField("coffee_id");
			coffeeID.setAccessible(true);
			coffeeID.get(coffee);
			// newOrder.add("t");
			// OrderDAOService.saveOrder(order);
			// CoffeeDAOService.saveCoffee(coffee);
		}
	}

	@FXML
	private void selectedByBrand(){
		Coffee coffeeItem = CoffeeDAOService.searchByBrand(brandBox.getValue());
		orderList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(brandBox);
	}
	@FXML
	private void selectedByName(){
		Coffee coffeeItem = CoffeeDAOService.searchByName(nameBox.getValue());
		orderList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(nameBox);
	}
	@FXML
	private void selectedByPrice(){
		Coffee coffeeItem = CoffeeDAOService.searchByPrice(priceBox.getValue());
		orderList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(priceBox);
	}
	@FXML
	private void selectedByRegion(){
		Coffee coffeeItem = CoffeeDAOService.searchByRegion(regionBox.getValue());
		orderList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(regionBox);
	}
	
	@FXML
	private void filterByRoast(){
		clearFilterTable();
		final List<Coffee> listByRoast = CoffeeDAOService.filterByRoast(roastBox.getValue());
		filteredList.addAll(listByRoast);
		loadFilterTable();
	}

	@FXML
	private void filterBySize(){
		clearFilterTable();
		final List<Coffee> listBySize = CoffeeDAOService.filterBySize(sizeBox.getValue());
		filteredList.addAll(listBySize);
		loadFilterTable();
	}

	//saves coffee table to DB once only
	private void saveCoffeeToDB() {
		if (!CoffeeDAOService.coffeeListExistsDB()) {
			for (Coffee coffee : CoffeeList.getCoffeeList()) {
				CoffeeDAOService.saveCoffeeTableToDB(coffee);
			}
		}
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

	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			timeDisplay.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd      HH:mm:ss")));
		}
	};
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

}

