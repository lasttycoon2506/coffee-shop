package artdealer;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void switchToCustomerPg() throws IOException {
        
        App.setRoot("customer");
    }
}
