package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import coffeeshop.App;
import coffeeshop.Data.CoffeeList;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
import coffeeshop.Entities.Customers.Customer;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;


public class CustomerOrders {
	private Customer customer = Context.getInstance().getCustomer();
	@FXML
    private TableView<Coffee> orderTable;
	@FXML
    private TableView<Coffee> filterTable;
	@FXML
	private ComboBox<String> brandBox;
	@FXML
	private ComboBox<String> nameBox;
	@FXML
	private ComboBox<String> roastBox;
	@FXML
	private ComboBox<String> priceBox;
	@FXML
	private ComboBox<String> regionBox;
	@FXML
	private ComboBox<Integer> sizeBox;
	@FXML
	private final ObservableList<Coffee> coffeeList = FXCollections.observableArrayList();
	private final ObservableList<Coffee> filterList = FXCollections.observableArrayList();
	private static final List<String> brandsList = CoffeeDAOService.getBrands();
	private static final List<String> namesList = CoffeeDAOService.getNames();
	private static final List<String> pricesList = CoffeeDAOService.getPrices();
	private static final List<String> regionsList = CoffeeDAOService.getRegions();
	private static final List<Integer> sizeList = CoffeeDAOService.getSizes();
	@FXML
	private TableColumn<Coffee, String> brandColumn;
	@FXML
	private TableColumn<Coffee, String> nameColumn;
	@FXML
	private TableColumn<Coffee, String> roastColumn;
	@FXML
	private TableColumn<Coffee, String> priceColumn;
	@FXML
	private TableColumn<Coffee, String> regionColumn;
	@FXML
	private TableColumn<Coffee, String> sizeColumn;
	@FXML
	private TableColumn<Coffee, Coffee> deleteColumn;
	@FXML
	private TableColumn<Coffee, String> brandColumnFilterTable;
	@FXML
	private TableColumn<Coffee, String> nameColumnFilterTable;
	@FXML
	private TableColumn<Coffee, String> roastColumnFilterTable;
	@FXML
	private TableColumn<Coffee, String> priceColumnFilterTable;
	@FXML
	private TableColumn<Coffee, String> regionColumnFilterTable;
	@FXML
	private TableColumn<Coffee, String> sizeColumnFilterTable;
	@FXML
	private TableColumn<Coffee, Coffee> addColumnFilterTable;
	public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
		initOrderTable();	
		initFilterTable();		
		saveCoffeeToDB();
		fillComboBoxes();	
    }


	private void initOrderTable(){
		brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeName"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("price"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("region"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeSize"));
		deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		deleteColumn.setCellFactory(param -> new TableCell<Coffee, Coffee>() {
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
					event -> getTableView().getItems().remove(coffee)
				);
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
	private void addToOrder() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException  {
        // Coffee coffeeItem = brandBox.getValue();
		// Field coffeeID = coffeeItem.getClass().getDeclaredField("coffee_id");
		// coffeeID.setAccessible(true);
		// System.out.println(coffeeID.get(coffeeItem));
	}

	@FXML
	private void selectedByBrand(){
		Coffee coffeeItem = CoffeeDAOService.searchByBrand(brandBox.getValue());
		coffeeList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(brandBox);
	}
	@FXML
	private void selectedByName(){
		Coffee coffeeItem = CoffeeDAOService.searchByName(nameBox.getValue());
		coffeeList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(nameBox);
	}
	@FXML
	private void selectedByPrice(){
		Coffee coffeeItem = CoffeeDAOService.searchByPrice(priceBox.getValue());
		coffeeList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(priceBox);
	}
	@FXML
	private void selectedByRegion(){
		Coffee coffeeItem = CoffeeDAOService.searchByRegion(regionBox.getValue());
		coffeeList.add(coffeeItem);
		loadOrderTable();
		resetChoiceBox(regionBox);
	}
	
	@FXML
	private void filterByRoast(){
		clearFilterTable();
		final List<Coffee> listByRoast = CoffeeDAOService.filterByRoast(roastBox.getValue());
		filterList.addAll(listByRoast);
		loadFilterTable();
	}

	@FXML
	private void filterBySize(){
		clearFilterTable();
		final List<Coffee> listBySize = CoffeeDAOService.filterBySize(sizeBox.getValue());
		filterList.addAll(listBySize);
		loadFilterTable();
	}

	//saves coffee table to DB once only
	private void saveCoffeeToDB() {
		if (!CoffeeDAOService.coffeeListExistsDB()) {
			for (Coffee coffee : CoffeeList.getCoffeeList()) {
				CoffeeDAOService.saveCoffee(coffee);
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
        orderTable.setItems(coffeeList);
	}
	private void loadFilterTable()  {
        filterTable.setItems(filterList);
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

}

