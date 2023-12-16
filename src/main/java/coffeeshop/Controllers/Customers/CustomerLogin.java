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
import coffeeshop.Entities.Customers.Customer;


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
	public void loginAttempt(ActionEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
        if (userLogin.getText().isEmpty() || pwLogin.getText().isEmpty()) {
            notificationWindow("blank field(s)");
        }
        else if (!CustomerDAOService.userNameExists(userLogin.getText())) {
            notificationWindow("error");
        }
        else if (CustomerDAOService.login(userLogin.getText(), pwLogin.getText()) == null){
            notificationWindow("user/pw incorrect");
        }
        else {
            Customer customer = CustomerDAOService.login(userLogin.getText(), pwLogin.getText());
            System.out.println(customer);
            switchToCustomerPg();
        }
    }


    private void notificationWindow(String windowType) {
        Alert dialog;
        if (windowType.equals("confirmation")) {
            dialog = new Alert(AlertType.CONFIRMATION, "Successfully Added!", ButtonType.OK);
        }
        else if (windowType.equals("blank field(s)")) {
            dialog = new Alert(AlertType.ERROR, "Missing Field(s)!", ButtonType.OK);
        }
        else if (windowType.equals("user/pw incorrect")) {
            dialog = new Alert(AlertType.ERROR, "User/Password Incorrect!", ButtonType.OK);
        }
        else {
            dialog = new Alert(AlertType.ERROR, "User Name Doesn't Exist!", ButtonType.OK);
        }
            dialog.show();
    }

    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
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