package coffeeshop.Controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import coffeeshop.App;


public class Home {
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customerLogin");
    }
}
