package coffeeshop.Controllers.Customers;

import java.io.IOException;
import coffeeshop.App;
import coffeeshop.Models.Context;
import coffeeshop.Entities.Customers.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, String> userCol;
	@FXML
	private TableColumn<Customer, String> pwCol;
	@FXML
	private TableColumn<Customer, String> firstNCol;
	@FXML
	private TableColumn<Customer, String> lastNCol;
	@FXML
	private TableColumn<Customer, String> emailCol;
	@FXML
	private TableColumn<Customer, String> phoneCol;

	public void initialize(){
        customer = Context.getInstance().getCustomer();
    }


	public void createTable() {
		customerTable.setEditable(true);
				
		customerTable.getItems().add(new Customer(customer.));
		userCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		userCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	

	
	// 	firstNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
	// 		@Override
	// 		public void handle(CellEditEvent<Person, String> event) {
	// 			Customer customer = event.getRowValue();
	// 			customer.setFirstName(event.getNewValue());
	// 		}
	// 	});
	// }
		


	// 	lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
	// 	lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	// 	lastNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
	// 		@Override
	// 		public void handle(CellEditEvent<Person, String> event) {
	// 			Person person = event.getRowValue();
	// 			person.setLastName(event.getNewValue());
	// 		}
	// 	});

	// 	ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
	// 	ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	// 	ageColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, Integer>>() {
	// 		@Override
	// 		public void handle(CellEditEvent<Person, Integer> event) {
	// 			Person person = event.getRowValue();
	// 			person.setAge(event.getNewValue());
	// 		}
	// 	});
		
	
		
	// 	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
	/
	// 	table.getItems().add(new Person("Daffy", "Duck", 83));
	// 	table.getItems().add(new Person("Foghorn", "Leghorn", 74));
	// 	table.getItems().add(new Person("Elmer", "Fudd", 83));
	// 	table.getItems().add(new Person("Tweety", "Bird", 73));
		
	// 	root.setCenter(table);

	// 	Scene scene = new Scene(root, 500, 300);
	// 	scene.getStylesheets().add("application/stylesheet.css");
	// 	primaryStage.setTitle("TableView Demo");
	// 	primaryStage.setScene(scene);
	// 	primaryStage.show();
	// }

    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

}

