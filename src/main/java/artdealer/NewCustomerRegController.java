package artdealer;

import java.io.IOException;
import javafx.fxml.FXML;

public class NewCustomerRegController {
    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("newCustomer");
    }

    @FXML
    void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

}
