module artdealer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens artdealer to javafx.fxml;
    exports artdealer;
    opens artdealer.Controllers to javafx.fxml;
    exports artdealer.Controllers;
}
