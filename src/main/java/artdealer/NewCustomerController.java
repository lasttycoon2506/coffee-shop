package artdealer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewCustomerController {

    @FXML
	private Label myLabel;
	@FXML
	private TextField firstNameEntry;
    @FXML
	private TextField lastNameEntry;
    @FXML
	private TextField emailEntry;
    @FXML
	private TextField phoneEntry;
	@FXML
	private Button myButton;
	
	public void submit(ActionEvent event) {
		
		
			System.out.println(firstNameEntry.getText());
            System.out.println(lastNameEntry.getText());
            System.out.println(emailEntry.getText());
            System.out.println(phoneEntry.getText());
			
			
	}

    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
}
