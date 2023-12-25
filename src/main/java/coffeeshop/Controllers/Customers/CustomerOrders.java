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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;


public class CustomerOrders {
	private Customer customer = Context.getInstance().getCustomer();
	@FXML
    private TableView<Coffee> table;
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
    private Button resetButton;
	public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
		brandColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("brand"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeName"));
        roastColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("roast"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("price"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("region"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Coffee, String>("coffeeSize"));		
		saveCoffeeToDB();
		fillComboBoxes();	
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
		loadTable();
	}
	@FXML
	private void selectedByName(){
		Coffee coffeeItem = CoffeeDAOService.searchByName(nameBox.getValue());
		coffeeList.add(coffeeItem);
		loadTable();
	}
	@FXML
	private void selectedByPrice(){
		Coffee coffeeItem = CoffeeDAOService.searchByPrice(priceBox.getValue());
		coffeeList.add(coffeeItem);
		loadTable();
	}
	@FXML
	private void selectedByRegion(){
		Coffee coffeeItem = CoffeeDAOService.searchByRegion(regionBox.getValue());
		coffeeList.add(coffeeItem);
		loadTable();
	}
	
	@FXML
	private void filterByRoast(){
		final List<Coffee> listByRoast = CoffeeDAOService.filterByRoast(roastBox.getValue());
		// brandBox.getItems().clear();
		// brandBox.getItems().addAll(listByRoast);
		// nameBox.getItems().clear();
		// nameBox.getItems().addAll(listByRoast);
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
		nameBox.setOnShown(event -> nameBox.show());
		nameBox.getSelectionModel().clearSelection();
		nameBox.setPromptText("Name");
	}
	private void resetRoast(){
		roastBox.setOnShown(event -> nameBox.show());
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
        table.setItems(coffeeList);
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

