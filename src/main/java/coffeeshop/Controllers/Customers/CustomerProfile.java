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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TableColumn;


public class CustomerProfile {
	@FXML
	private Customer customer;
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
        customer = Context.getInstance().getCustomer();
		data = FXCollections.observableArrayList( new Customer(customer.getUserName(), customer.getPassword(), customer.getFirstName(),
														customer.getLastName(), customer.getEmail(), customer.getPhone())
        );
    }
	

	public void createTable()  {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pwColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstNColumn
.setCellValueFactory(new PropertyValueFactory<>("birth"));
        lastNColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		// table.setItems(null);
		
		

		
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

