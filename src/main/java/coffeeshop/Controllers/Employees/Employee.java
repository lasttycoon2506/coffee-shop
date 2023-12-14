package coffeeshop.Controllers.Employees;

import java.io.IOException;
import coffeeshop.App;
import javafx.fxml.FXML;


public class Employee {

    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}