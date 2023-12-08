package coffeeshop.Controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import coffeeshop.App;


public class HomeController {

    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }

      @FXML
    void switchToEmployeePg() throws IOException {
        App.setRoot("employee");
    }

}
