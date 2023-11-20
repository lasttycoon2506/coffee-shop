package artdealer;

import java.io.IOException;
import javafx.fxml.FXML;

public class CustomerController {

    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}