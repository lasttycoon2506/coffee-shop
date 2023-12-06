module coffeeshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens coffeeshop to javafx.fxml;
    exports coffeeshop;
    opens coffeeshop.Controllers to javafx.fxml;
    exports coffeeshop.Controllers;
}
