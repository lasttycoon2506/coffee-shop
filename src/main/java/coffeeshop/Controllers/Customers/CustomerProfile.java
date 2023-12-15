package coffeeshop.Controllers.Customers;

import java.io.IOException;
import coffeeshop.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class CustomerProfile {

    TableView<Person> table = new TableView<Person>();
		table.setEditable(true);
				
		TableColumn<Person, String> firstNameColumn = new TableColumn<Person, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		firstNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> event) {
				Person person = event.getRowValue();
				person.setFirstName(event.getNewValue());
			}
		});


		TableColumn<Person, String> lastNameColumn = new TableColumn<Person, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		lastNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> event) {
				Person person = event.getRowValue();
				person.setLastName(event.getNewValue());
			}
		});

		TableColumn<Person, Integer> ageColumn = new TableColumn<Person, Integer>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
		ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		ageColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, Integer>>() {
			@Override
			public void handle(CellEditEvent<Person, Integer> event) {
				Person person = event.getRowValue();
				person.setAge(event.getNewValue());
			}
		});
		
		table.getColumns().add(firstNameColumn);
		table.getColumns().add(lastNameColumn);
		table.getColumns().add(ageColumn);
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.getItems().add(new Person("Buggs", "Bunny", 79));
		table.getItems().add(new Person("Daffy", "Duck", 83));
		table.getItems().add(new Person("Foghorn", "Leghorn", 74));
		table.getItems().add(new Person("Elmer", "Fudd", 83));
		table.getItems().add(new Person("Tweety", "Bird", 73));
		
		root.setCenter(table);

		Scene scene = new Scene(root, 500, 300);
		scene.getStylesheets().add("application/stylesheet.css");
		primaryStage.setTitle("TableView Demo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customerLogin");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}
