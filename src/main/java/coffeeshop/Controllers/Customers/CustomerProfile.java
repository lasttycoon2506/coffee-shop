package coffeeshop.Controllers.Customers;

import java.io.IOException;
import coffeeshop.App;
import javafx.fxml.FXML;


public class CustomerProfile {
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}
