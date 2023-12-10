package coffeeshop.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import org.hibernate.JDBCException;
import coffeeshop.App;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Entities.Customers.CustomerDAOService;
import coffeeshop.Models.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


public class NewCustomerController {
    List<String> errorList = new ArrayList<String>();
    static String emailErr;
    
    @FXML
    private Label userLabel;
    @FXML
    private Label pwLabel;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
	private TextField userEntry;
    @FXML
	private TextField pwEntry;
	@FXML
	private TextField fNameEntry;
    @FXML
	private TextField lNameEntry;
    @FXML
	private TextField emailEntry;
    @FXML
    private TextField phoneEntry;
	@FXML
	private Button myButton;

    // pw doesnt accept space
    UnaryOperator<TextFormatter.Change> spaceFilter = change -> {
        if (change.getText().equals(" ")) {
            change.setText("");
        }
        return change;
    };
    // phone field allows numbers only
    UnaryOperator<TextFormatter.Change> phoneFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?([1-9][0-9]*)?")) {
            return change;
        }
        return null;
    };
    // inits pw/phone fields using above filters
    public void initialize(){
        pwEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        phoneEntry.setTextFormatter(new TextFormatter<>(phoneFilter));
    }
	
     
    @FXML
	public void submit(ActionEvent event) throws IOException, SQLException, JDBCException {
        if (userEntry.getText().trim().isEmpty() || pwEntry.getText().trim().isEmpty()
            || fNameEntry.getText().trim().isEmpty() || lNameEntry.getText().trim().isEmpty()
            || emailEntry.getText().trim().isEmpty() || phoneEntry.getText().trim().isEmpty()) {
            
            if (userEntry.getText().trim().isEmpty()){
                userLabel.setTextFill(Color.color(1, 0, 0));
                userLabel.setText("Enter Username!");
            }
            if (pwEntry.getText().trim().isEmpty()){
                pwLabel.setTextFill(Color.color(1, 0, 0));
                pwLabel.setText("Enter Password!");
            }
            if (fNameEntry.getText().trim().isEmpty()){
                fNameLabel.setTextFill(Color.color(1, 0, 0));
                fNameLabel.setText("Enter First Name!");
            }
            if (lNameEntry.getText().trim().isEmpty()){
                lNameLabel.setTextFill(Color.color(1, 0, 0));
                lNameLabel.setText("Enter Last Name!");
            }
            if (emailEntry.getText().trim().isEmpty()){
                emailLabel.setTextFill(Color.color(1, 0, 0));
                emailLabel.setText("Enter Email!");
            }
            if (phoneEntry.getText().trim().isEmpty()){
                phoneLabel.setTextFill(Color.color(1, 0, 0));
                phoneLabel.setText("Enter Phone!");
            }
        }
        else {
            if (!CustomerDAOService.userNameExists(userEntry.getText())){
                    Alert dialog = new Alert(AlertType.ERROR, "Error:", ButtonType.OK);
                    dialog.show();
                }
            if (!pwValidator(pwEntry, errorList)){
                pwLabel.setTextFill(Color.color(1, 0, 0));
                for (String error: errorList) {
                    pwLabel.setText(error);
                } 
                if (!emailValidator(emailEntry)){
                    emailLabel.setTextFill(Color.color(1, 0, 0));
                    emailLabel.setText(emailErr);
                }
            }
            else if (!emailValidator(emailEntry)){
                emailLabel.setTextFill(Color.color(1, 0, 0));
                emailLabel.setText(emailErr);
            }
            else {
                CustomerDTO newEntry = new CustomerDTO(userEntry.getText(), pwEntry.getText(), fNameEntry.getText(), 
                                            lNameEntry.getText(), emailEntry.getText(), phoneEntry.getText());  

                    CustomerDAOService.saveCustomer(new Customer(newEntry.userName(), newEntry.password(), newEntry.firstName(), 
                                                        newEntry.lastName(), newEntry.email(), newEntry.phone()));
                    switchToCustomerRegisteredPg();
                
                
                    // Alert dialog = new Alert(AlertType.ERROR, "Error:", ButtonType.OK);
                    // dialog.show();
                    
            }
        }
	}
    

    public static boolean pwValidator(TextField pwField, List<String> errorList) {
        String pw = pwField.getText().trim();
        Pattern specialChar = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern number = Pattern.compile("[0-9]");
        errorList.clear();
        boolean flag=true;

        if (pw.length() < 8) {
            errorList.add("Password must be at least 8 Characters!");
            flag=false;
        }
        if (!specialChar.matcher(pw).find()) {
            errorList.add("Password must have at least one \n Special Character!");
            flag=false;
        }
        if (!upperCase.matcher(pw).find()) {
            errorList.add("Password must have at least one \n Upper Case letter!");
            flag=false;
        }
        if (!lowerCase.matcher(pw).find()) {
            errorList.add("Password must have at least one \n Lower Case letter!");
            flag=false;
        }
        if (!number.matcher(pw).find()) {
            errorList.add("Password must have at least one Number!");
            flag=false;
        }
        return flag;
    }


    // email field accepts only @address...
    public static boolean emailValidator(TextField emailField) {
        String email = emailField.getText().trim();
        Pattern gmailPattern = Pattern.compile("^.*@gmail.com.*$");
        Pattern yahooPattern = Pattern.compile("^.*@yahoo.com.*$");
        Pattern icloudPattern = Pattern.compile("^.*@icloud.com.*$");
        Pattern outlookPattern = Pattern.compile("^.*@outlook.com.*$");
        boolean flag=true;

        if (gmailPattern.matcher(email).find() || yahooPattern.matcher(email).find()
            || icloudPattern.matcher(email).find() || outlookPattern.matcher(email).find()) {
            return flag;
            }
        else {
            emailErr = "Email must contain: \n @gmail \n @yahoo \n @icloud \n @outlook";
            flag = false;
            return flag;
        }
    }


    //clears empty label alert upon text entered
    @FXML
    void resetUserLabel(KeyEvent event) {
        userLabel.setText("");
    }
    @FXML
    void resetPwLabel(KeyEvent event) {
        pwLabel.setText("");
    }
    @FXML
    void resetFnameLabel(KeyEvent event) {
        fNameLabel.setText("");
    }
    @FXML
    void resetLnameLabel(KeyEvent event) {
        lNameLabel.setText("");
    }
    @FXML
    void resetEmailLabel(KeyEvent event) {
        emailLabel.setText("");
    }
    @FXML
    void resetPhoneLabel(KeyEvent event) {
        phoneLabel.setText("");
    }


    //page navigation...
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    @FXML
    private void switchToCustomerRegisteredPg() throws IOException {
        App.setRoot("newCustomerRegistered");
    }
}
