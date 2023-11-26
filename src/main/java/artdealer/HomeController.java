package artdealer;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeController {
	
	// @FXML
	// ImageView imageView;
	
	// Image myImage = new Image(getClass().getResourceAsStream("coffee.jpg"));
	
	// public void displayImage() {
	// 	imageView.setImage(myImage);
	// }

    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }

    @FXML
    private void switchToEmployeePg() throws IOException {
        App.setRoot("employee");
    }

}
