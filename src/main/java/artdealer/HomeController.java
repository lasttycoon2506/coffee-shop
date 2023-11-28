package artdealer;

import javafx.fxml.FXML;
import java.io.IOException;

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
