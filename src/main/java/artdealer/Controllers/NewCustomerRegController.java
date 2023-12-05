package artdealer.Controllers;

import java.io.IOException;
import artdealer.App;
import javafx.fxml.FXML;


public class NewCustomerRegController {

    //display newly added customer...


    @FXML
    void switchToNewCustomerPg() throws IOException {
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
