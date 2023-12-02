package artdealer.Controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import artdealer.App;

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
