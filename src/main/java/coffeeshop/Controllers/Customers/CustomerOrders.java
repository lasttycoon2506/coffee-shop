package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import coffeeshop.App;
import coffeeshop.Data.CoffeeList;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
import coffeeshop.Entities.Customers.Customer;
import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableView;
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
	private ComboBox<Coffee> brandBox;
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
	private ObservableList<Customer> data;
	@FXML
	private List<Coffee> coffeeList = CoffeeDAOService.getCoffeeList();
	@FXML
	private TableColumn<Customer, String> userColumn;
	@FXML
	private TableColumn<Customer, String> pwColumn;
	@FXML
	private TableColumn<Customer, String> firstNColumn;
	//inits list cells with coffee objects brand
	Callback<ListView<Coffee>, ListCell<Coffee>> cellFactory = lv -> new ListCell<Coffee>() {
		@Override
		protected void updateItem(Coffee item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? "" : item.getBrand());
		}
	};
	public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
		// data = FXCollections.observableArrayList(new Customer(customer.getUserName(), customer.getPassword(), customer.getFirstName(),
		// 												customer.getLastName(), customer.getEmail(), customer.getPhone())
        // );
		// loadTable();
		brandBox.setButtonCell(cellFactory.call(null));
		brandBox.setCellFactory(cellFactory);
		brandBox.getItems().addAll(coffeeList);
		saveCoffeeToDB();	
    }
	
	@FXML
	private void addToOrder() {
		Coffee t = brandBox.getValue();
		System.out.println("Dg");
	}

	private void saveCoffeeToDB() {
		//checks if coffee table already populated
		if (!CoffeeDAOService.coffeeListExistsDB()) {
			for (Coffee coffee : CoffeeList.getCoffeeList()) {
				CoffeeDAOService.saveCoffee(coffee);
			}
		}
	}

	private void loadTable()  {
		table.setEditable(true);
        // userColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>(Coffee.COFFEE1.getBrand()));
        // pwColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("pWord"));
        // firstNColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		// lastNColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        // emailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        // phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        table.setItems(data);
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

