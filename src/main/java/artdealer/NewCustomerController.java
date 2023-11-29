package artdealer;

import java.io.IOException;
import artdealer.Models.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
	
    @FXML
	void submit(ActionEvent event) throws IOException {
        if (firstNameEntry.getText().trim().isEmpty() || lastNameEntry.getText().trim().isEmpty() || emailEntry.getText().trim().isEmpty() || phoneEntry.getText().trim().isEmpty()
            || firstNameEntry.getText().equals("Please Enter First Name!") || lastNameEntry.getText().equals("Please Enter Last Name!")
            || emailEntry.getText().equals("Please Enter Email!") || phoneEntry.getText().equals("Please Enter Phone Number!")) {
            
            if(firstNameEntry.getText().trim().isEmpty()){
                firstNameEntry.setText("Please Enter First Name!");
            }
            if(firstNameEntry.getText().equals("Please Enter First Name!")){
                resetFnameField(null);
            }

            if(lastNameEntry.getText().trim().isEmpty()){
                lastNameEntry.setText("Please Enter Last Name!");
            }
            if(lastNameEntry.getText().equals("Please Enter Last Name!")){
                resetLnameField(null);
            }

            if(emailEntry.getText().trim().isEmpty()){
                emailEntry.setText("Please Enter Email!");
            }
            if(emailEntry.getText().equals("Please Enter Email!")){
                resetEmailField(null);
            }

            if(phoneEntry.getText().trim().isEmpty()){
                phoneEntry.setText("Please Enter Phone Number!");
            }
            if(phoneEntry.getText().equals("Please Enter Phone Number!")){
                resetPhoneField(null);;
            }
        }
        else {
		CustomerDTO newEntry = new CustomerDTO(firstNameEntry.getText(), lastNameEntry.getText(), emailEntry.getText(), phoneEntry.getText());
        switchToCustomerRegisteredPg();
        }
	}

    @FXML
    void resetFnameField(MouseEvent event) {
        if(firstNameEntry.getText() != null){
            firstNameEntry.setText("");
        }
    }

    @FXML
    void resetLnameField(MouseEvent event) {
        if(lastNameEntry.getText() != null){
            lastNameEntry.setText("");
        }
    }

    @FXML
    void resetEmailField(MouseEvent event) {
        if(emailEntry.getText() != null){
            emailEntry.setText("");
        }
    }

    @FXML
    void resetPhoneField(MouseEvent event) {
        if(phoneEntry.getText() != null){
            phoneEntry.setText("");
        }
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
