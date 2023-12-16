package coffeeshop.Controllers.Customers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import org.hibernate.JDBCException;
import coffeeshop.App;
import coffeeshop.Entities.Customers.Customer;
import coffeeshop.Entities.Customers.CustomerDAOService;
import coffeeshop.Models.Context;
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


public class NewCustomer {
    List<String> errorList = new ArrayList<String>();
    static String emailErr;
    static String phoneLenErr;

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
	public void submit(ActionEvent event) throws IOException, SQLException, JDBCException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (userEntry.getText().trim().isEmpty() || pwEntry.getText().isEmpty()
            || fNameEntry.getText().trim().isEmpty() || lNameEntry.getText().trim().isEmpty()
            || emailEntry.getText().trim().isEmpty() || phoneEntry.getText().isEmpty()) {
            
            if (userEntry.getText().trim().isEmpty()){
                notificationWindow("error", "User Name Empty!");
            }
            else if (pwEntry.getText().isEmpty()){
                notificationWindow("error", "Password Empty!");
            }
            else if (fNameEntry.getText().trim().isEmpty()){
                notificationWindow("error", "First Name Empty!");
            }
            else if (lNameEntry.getText().trim().isEmpty()){
                notificationWindow("error", "Last Name Empty!");
            }
            else if (emailEntry.getText().trim().isEmpty()){
               notificationWindow("error", "Email Empty!");
            }
            else if (phoneEntry.getText().isEmpty()){
                notificationWindow("error", "Phone Empty!");
            }
        }
        else {
            if (CustomerDAOService.userNameExists(userEntry.getText())){
                notificationWindow("error", "User Name Exists!");
                }
            else if (CustomerDAOService.emailExists(emailEntry.getText())){
                notificationWindow("error", "Email Exists!");
                }
            else if (CustomerDAOService.phoneExists(phoneEntry.getText())){
                notificationWindow("error", "Phone Exists!");

                }
            if (!pwValidator(pwEntry, errorList)){
                notificationWindow("error", stringFormatter(errorList));
            }
            else if (!emailValidator(emailEntry)){
                notificationWindow("error", emailErr);
            }
            else if (!phoneLenValidator(phoneEntry)){
                notificationWindow("error", phoneLenErr);
            }
            else if (!emailValidator(emailEntry)){
                notificationWindow("error", emailErr);
            }
            else {
                CustomerDTO newEntry = new CustomerDTO(userEntry.getText().trim(), pwEntry.getText(), fNameEntry.getText().trim(), 
                                            lNameEntry.getText().trim(), emailEntry.getText().trim(), phoneEntry.getText());  

                CustomerDAOService.saveCustomer(new Customer(newEntry.userName(), newEntry.password(), newEntry.firstName(), 
                                                        newEntry.lastName(), newEntry.email(), newEntry.phone()));
                notificationWindow("confirmation", null);
                switchToCustomerPg();
            }
        }
	}
    

    public static boolean pwValidator(TextField pwField, List<String> errorList) {
        String pw = pwField.getText();
        Pattern specialChar = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern number = Pattern.compile("[0-9]");
        errorList.clear();
        boolean flag=true;

        if (pw.length() < 8) {
            errorList.add(" Password must be at least 8 Characters!\n");
            flag=false;
        }
        if (!specialChar.matcher(pw).find()) {
            errorList.add("Password must have at least one Special Character!\n");
            flag=false;
        }
        if (!upperCase.matcher(pw).find()) {
            errorList.add("Password must have at least one Upper Case letter!\n");
            flag=false;
        }
        if (!lowerCase.matcher(pw).find()) {
            errorList.add("Password must have at least one Lower Case letter!\n");
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

        if (gmailPattern.matcher(email).find() || yahooPattern.matcher(email).find()
            || icloudPattern.matcher(email).find() || outlookPattern.matcher(email).find()) {
            return true;
            }
        else {
            emailErr = "Email must contain: \n @gmail \n @yahoo \n @icloud \n @outlook";
            return false;
        }
    }


    public static boolean phoneLenValidator(TextField phoneField) {
        String phone = phoneField.getText();
        if (phone.length() != 10) {
            phoneLenErr = "Phone Number must be 10 digits!";
            return false;
        }
        return true;
    }


    public String stringFormatter(List lst) {
        String listToStr = Arrays.toString(lst.toArray()).replace("[", "").replace("]", "").replace(",", "");
        return listToStr;
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
    private void switchToCustomerLoginPg() throws IOException {
        App.setRoot("customerLogin");
    }
    @FXML
    private void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    

    private void notificationWindow(String windowType, String message) {
        Alert dialog;
        if (windowType.equals("confirmation")) {
            dialog = new Alert(AlertType.CONFIRMATION, "Successfully Added!", ButtonType.OK);
        }
        else {
            dialog = new Alert(AlertType.ERROR, message, ButtonType.OK);
        }
        dialog.show();

    }
}
