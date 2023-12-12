package coffeeshop.Controllers;

import java.io.IOException;
import coffeeshop.App;
import coffeeshop.Entities.Customers.CustomerDAOService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        if (!CustomerDAOService.userNameExists(userLogin.getText())){
                    notificationWindow("error");
                }
                
    }

    private void notificationWindow(String windowType) {
        Alert dialog;
        if (windowType.equals("confirmation")) {
            dialog = new Alert(AlertType.CONFIRMATION, "Successfully Added!", ButtonType.OK);
        }
        else {
            dialog = new Alert(AlertType.ERROR, "User Name Doesn't Exist!", ButtonType.OK);
        }
            dialog.show();
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