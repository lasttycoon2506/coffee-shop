package coffeeshop.Controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import coffeeshop.App;


public class Home {

    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customerLogin");
    }

      @FXML
    void switchToEmployeePg() throws IOException {
        App.setRoot("employee");
    }

}
