package artdealer;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.event.ActionEvent;

public class HomeController {

    @FXML
    void switchToCustomerPg(ActionEvent event) throws IOException {
        App.setRoot("customer");
    }

      @FXML
    void switchToEmployeePg() throws IOException {
        App.setRoot("employee");
    }

}
