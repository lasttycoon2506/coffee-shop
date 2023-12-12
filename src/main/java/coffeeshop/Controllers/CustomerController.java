package coffeeshop.Controllers;

import java.io.IOException;
import coffeeshop.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class CustomerController {
    @FXML
	private TextField userLogin;
    @FXML
	private TextField pwLogin;
    @FXML
	private Button myButton;

    @FXML
	public void loginAttempt(ActionEvent event) {
        
    }

    @FXML
    void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToNewCustomerPg() throws IOException {
        App.setRoot("newCustomer");
    }

}