package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import coffeeshop.App;
import coffeeshop.Data.CoffeeList;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
import coffeeshop.Entities.Customers.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;


public class CustomerOrders {
	@FXML
	private Customer customer = Context.getInstance().getCustomer();
	@FXML
    private TableView<Customer> table;
	@FXML
	private ComboBox<String> brandBox;
	@FXML
	private ComboBox<String> coffeeNameBox;
	@FXML
	private ComboBox<String> roastBox;
	@FXML
	private ComboBox<Float> priceBox;
	@FXML
	private ComboBox<String> regionBox;
	@FXML
	private ComboBox<Integer> sizeBox;
	@FXML
	private ObservableList<Coffee> data;
	private static final List<String> brandsList = CoffeeDAOService.getBrands();
	private static final List<String> coffeeNamesList = CoffeeDAOService.getCoffeeNames();
	private static final List<String> roastsList = CoffeeDAOService.getRoasts();
	private static final List<Float> pricesList = CoffeeDAOService.getPrices();
	@FXML
	private TableColumn<Coffee, String> brandColumn;
	@FXML
	private TableColumn<Coffee, String> coffeeNameColumn;
	@FXML
	private TableColumn<Coffee, String> roastColumn;
	@FXML
	private TableColumn<Coffee, String> priceColumn;
	@FXML
	private TableColumn<Coffee, String> regionColumn;
	@FXML
	private TableColumn<Coffee, String> sizeColumn;
	@FXML
    private Button resetButton;
	//inits list cells with coffee objects' specific property
	// Callback<ListView<Coffee>, ListCell<Coffee>> cellFactoryBrand = lv -> new ListCell<Coffee>() {
	// 	@Override
	// 	protected void updateItem(Coffee item, boolean empty) {
	// 		super.updateItem(item, empty);
	// 		setText(empty ? "Brand" : item.getBrand());
	// 	}
	// };
	// Callback<ListView<Coffee>, ListCell<Coffee>> cellFactoryCoffeeName = lv -> new ListCell<Coffee>() {
	// 	@Override
	// 	protected void updateItem(Coffee item, boolean empty) {
	// 		super.updateItem(item, empty);
	// 		setText(empty ? "Name" : item.getCoffeeName());
	// 	}
	// };
	// Callback<ListView<Coffee>, ListCell<Coffee>> cellFactoryPrice = lv -> new ListCell<Coffee>() {
	// 	@Override
	// 	protected void updateItem(Coffee item, boolean empty) {
	// 		super.updateItem(item, empty);
	// 		setText(empty ? "Price" : Float.toString(item.getPrice()));
	// 	}
	// };
	// Callback<ListView<Coffee>, ListCell<Coffee>> cellFactoryRegion = lv -> new ListCell<Coffee>() {
	// 	@Override
	// 	protected void updateItem(Coffee item, boolean empty) {
	// 		super.updateItem(item, empty);
	// 		setText(empty ? "Region" : item.getRegion());
	// 	}
	// };
	// Callback<ListView<Coffee>, ListCell<Coffee>> cellFactorySize = lv -> new ListCell<Coffee>() {
	// 	@Override
	// 	protected void updateItem(Coffee item, boolean empty) {
	// 		super.updateItem(item, empty);
	// 		setText(empty ? "Size" : Integer.toString(item.getCoffeeSize()));
	// 	}
	// };
	public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
		saveCoffeeToDB();
		fillComboBoxes();	
    }
	
	//sets & fills combo boxes with coffee objects w/ chooseable properties
	private void fillComboBoxes() {
		// brandBox.setButtonCell(cellFactoryBrand.call(null));
		// brandBox.setCellFactory(cellFactoryBrand);
		brandBox.getItems().addAll(brandsList);
		// coffeeNameBox.setButtonCell(cellFactoryCoffeeName.call(null));
		// coffeeNameBox.setCellFactory(cellFactoryCoffeeName);
		coffeeNameBox.getItems().addAll(coffeeNamesList);
		roastBox.getItems().addAll(roastsList);
		// priceBox.setButtonCell(cellFactoryPrice.call(null));
		// priceBox.setCellFactory(cellFactoryPrice);
		priceBox.getItems().addAll(pricesList);
		// regionBox.setButtonCell(cellFactoryRegion.call(null));
		// regionBox.setCellFactory(cellFactoryRegion);
		// regionBox.getItems().addAll(coffeeList);
		// sizeBox.setButtonCell(cellFactorySize.call(null));
		// sizeBox.setCellFactory(cellFactorySize);
		// sizeBox.getItems().addAll(coffeeList);
	}

	@FXML
	private void addToOrder() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException  {
        // Coffee coffeeItem = brandBox.getValue();
		// Field coffeeID = coffeeItem.getClass().getDeclaredField("coffee_id");
		// coffeeID.setAccessible(true);
		// System.out.println(coffeeID.get(coffeeItem));
	}

	@FXML // if unique coffee object is selected by property this prevents others from being selected
	private void selectedByBrand(){
		brandBox.setOnShown(event -> coffeeNameBox.hide());
		brandBox.setPromptText("");
		coffeeNameBox.setOnShown(event -> coffeeNameBox.hide());
		coffeeNameBox.setPromptText("");
		roastBox.setOnShown(event -> roastBox.hide());
		roastBox.setPromptText("");
		priceBox.setOnShown(event -> priceBox.hide());
		priceBox.setPromptText("");
		regionBox.setOnShown(event -> regionBox.hide());
		regionBox.setPromptText("");
		sizeBox.setOnShown(event -> sizeBox.hide());
		sizeBox.setPromptText("");
		// data = FXCollections.observableArrayList(brandBox.getValue(), coffeeNameBox.getValue(), roastBox.getValue(),
		// 										priceBox.getValue(), regionBox.getValue(), sizeBox.getValue());
		loadTable();
	}
	@FXML
	private void selectedByCoffeeName(){
		brandBox.setOnShown(event -> brandBox.hide());
		brandBox.setPromptText("");
		roastBox.setOnShown(event -> roastBox.hide());
		roastBox.setPromptText("");
		priceBox.setOnShown(event -> priceBox.hide());
		priceBox.setPromptText("");
		regionBox.setOnShown(event -> regionBox.hide());
		regionBox.setPromptText("");
		sizeBox.setOnShown(event -> sizeBox.hide());
		sizeBox.setPromptText("");
	}
	@FXML
	private void selectedByPrice(){
		brandBox.setOnShown(event -> brandBox.hide());
		brandBox.setPromptText("");
		coffeeNameBox.setOnShown(event -> coffeeNameBox.hide());
		coffeeNameBox.setPromptText("");
		roastBox.setOnShown(event -> roastBox.hide());
		roastBox.setPromptText("");
		regionBox.setOnShown(event -> regionBox.hide());
		regionBox.setPromptText("");
		sizeBox.setOnShown(event -> sizeBox.hide());
		sizeBox.setPromptText("");
	}
	@FXML
	private void selectedByRegion(){
		brandBox.setOnShown(event -> brandBox.hide());
		brandBox.setPromptText("");
		coffeeNameBox.setOnShown(event -> coffeeNameBox.hide());
		coffeeNameBox.setPromptText("");
		roastBox.setOnShown(event -> roastBox.hide());
		roastBox.setPromptText("");
		priceBox.setOnShown(event -> priceBox.hide());
		priceBox.setPromptText("");
		sizeBox.setOnShown(event -> sizeBox.hide());
		sizeBox.setPromptText("");
	}
	
	@FXML
	private void filterByRoast(){
		final List<Coffee> listByRoast = CoffeeDAOService.filterByRoast(roastBox.getValue());
		// brandBox.getItems().clear();
		// brandBox.getItems().addAll(listByRoast);
		// coffeeNameBox.getItems().clear();
		// coffeeNameBox.getItems().addAll(listByRoast);
		// priceBox.getItems().clear();
		// priceBox.getItems().addAll(listByRoast);
		// regionBox.getItems().clear();
		// regionBox.getItems().addAll(listByRoast);
		// sizeBox.getItems().clear();
		// sizeBox.getItems().addAll(listByRoast);
	}

	//saves coffee table to DB once only
	private void saveCoffeeToDB() {
		if (!CoffeeDAOService.coffeeListExistsDB()) {
			for (Coffee coffee : CoffeeList.getCoffeeList()) {
				CoffeeDAOService.saveCoffee(coffee);
			}
		}
	}

	@FXML
	private void resetCoffeeFields(){
		resetBrand();
		resetCoffeeName();
		resetRoast();
		resetPrice();
		resetRegion();
		resetSize();
	}

	private void resetBrand(){
		brandBox.setOnShown(event -> brandBox.show());
		brandBox.getSelectionModel().clearSelection();
		brandBox.setPromptText("Brand");
	}
	private void resetCoffeeName(){
		coffeeNameBox.setOnShown(event -> coffeeNameBox.show());
		coffeeNameBox.getSelectionModel().clearSelection();
		coffeeNameBox.setPromptText("Name");
	}
	private void resetRoast(){
		roastBox.setOnShown(event -> coffeeNameBox.show());
		roastBox.getSelectionModel().clearSelection();
		roastBox.setPromptText("Roast");
	}
	private void resetPrice(){
		priceBox.setOnShown(event -> priceBox.show());
		priceBox.valueProperty().set(null);
		priceBox.setPromptText("Price");
	}
	private void resetRegion(){
		regionBox.setOnShown(event -> regionBox.show());
		regionBox.valueProperty().set(null);
		regionBox.setPromptText("Region");
	}
	private void resetSize(){
		sizeBox.setOnShown(event -> sizeBox.show());
		sizeBox.valueProperty().set(null);
		sizeBox.setPromptText("Size");
	}


	private void loadTable()  {
		table.setEditable(true);
        brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("tt"));
        coffeeNameColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("pWord"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("firstName"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("lastName"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("email"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("phone"));
        // table.setItems(data);
		// private ObservableList<String> brands = FXCollections.observableArrayList(Coffee.COFFEE1.getBrand(), Coffee.COFFEE2.getBrand());
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

