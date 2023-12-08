package coffeeshop.Controllers;

import java.io.IOException;
import coffeeshop.App;
import javafx.fxml.FXML;


public class EmployeeController {

    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}