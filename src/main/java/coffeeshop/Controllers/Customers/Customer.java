package coffeeshop.Controllers.Customers;

import java.io.IOException;
import coffeeshop.App;
import javafx.fxml.FXML;


public class Customer {
    @FXML
    private void switchToCustomerNewOrdersPg() throws IOException {
        App.setRoot("customerNewOrders");
    }
    @FXML
    private void switchToCustomerProfilePg() throws IOException {
        App.setRoot("customerProfile");
    }
    @FXML
    private void switchToLoginPg() throws IOException {
        App.setRoot("customerLogin");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}
