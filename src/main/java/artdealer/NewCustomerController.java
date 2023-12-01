package artdealer;

import java.io.IOException;
import artdealer.Models.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class NewCustomerController {
    @FXML
    private Label fNameLabel;
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
	
    @FXML
	void submit(ActionEvent event) throws IOException {
        
            
            if(firstNameEntry.getText().trim().isEmpty()){
                fNameLabel.setTextFill(Color.color(1, 0, 0));
                fNameLabel.setText("Enter First Name!");
            }
           
        
        else {
		CustomerDTO newEntry = new CustomerDTO(firstNameEntry.getText(), lastNameEntry.getText(), emailEntry.getText(), phoneEntry.getText());
        switchToCustomerRegisteredPg();
        }
	}

    @FXML
    void resetFnameLabel(KeyEvent event) {
        fNameLabel.setText("");
    }


    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }

    @FXML
    private void switchToCustomerRegisteredPg() throws IOException {
        App.setRoot("newCustomerRegistered");
    }
}
