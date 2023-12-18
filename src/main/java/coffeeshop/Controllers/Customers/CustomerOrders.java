package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import coffeeshop.App;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Customers.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class CustomerOrders {
	@FXML
	private static Customer customer = Context.getInstance().getCustomer();
	@FXML
    private TableView<Customer> table;
	@FXML
	private ObservableList<Customer> data;
	@FXML
	private TableColumn<Customer, String> userColumn;
	@FXML
	private TableColumn<Customer, String> pwColumn;
	@FXML
	private TableColumn<Customer, String> firstNColumn;
	@FXML
	private TableColumn<Customer, String> lastNColumn;
	@FXML
	private TableColumn<Customer, String> emailColumn;
	@FXML
	private TableColumn<Customer, String> phoneColumn;
	
	public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
		data = FXCollections.observableArrayList(new Customer(customer.getUserName(), customer.getPassword(), customer.getFirstName(),
														customer.getLastName(), customer.getEmail(), customer.getPhone())
        );
		loadTable();
    }
	

	private void loadTable()  {
		table.setEditable(true);
        userColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("userName"));
        pwColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("pWord"));
        firstNColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		lastNColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        table.setItems(data);
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

