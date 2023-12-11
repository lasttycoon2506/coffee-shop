package coffeeshop.Controllers;

import java.io.IOException;
import coffeeshop.App;
import javafx.fxml.FXML;


public class LoginController {
    @FXML
    void switchToLoginPg() throws IOException {
        App.setRoot("newCustomer");
    }

    @FXML
    void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToNewOrderPg() throws IOException {
        App.setRoot("home");
    }
}
