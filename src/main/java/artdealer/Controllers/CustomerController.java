package artdealer.Controllers;

import java.io.IOException;

import artdealer.App;
import javafx.fxml.FXML;

public class CustomerController {

    @FXML
    void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToNewCustomerPg() throws IOException {
        App.setRoot("newCustomer");
    }

}