package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.util.function.UnaryOperator;

import coffeeshop.App;
import coffeeshop.Entities.Customers.CustomerDAOService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class CustomerLogin {
    @FXML
	private TextField userLogin;
    @FXML
	private TextField pwLogin;
    @FXML
	private Button myButton;

    UnaryOperator<TextFormatter.Change> spaceFilter = change -> {
        if (change.getText().equals(" ")) {
            change.setText("");
        }
        return change;
    };

    public void initialize(){
        userLogin.setTextFormatter(new TextFormatter<>(spaceFilter));
        pwLogin.setTextFormatter(new TextFormatter<>(spaceFilter));
    }
    

    @FXML
	public void loginAttempt(ActionEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (!CustomerDAOService.userNameExists(userLogin.getText())){
                    notificationWindow("error");
                }
        CustomerDAOService.login(userLogin.getText().trim(), pwLogin.getText());
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