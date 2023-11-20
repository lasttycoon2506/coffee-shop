package artdealer;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        
	// TextField nameTextField;
        App.setRoot("secondary");
    }
}
