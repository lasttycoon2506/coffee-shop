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
import coffeeshop.Entities.Customers.CustomerDAOService;
import coffeeshop.Models.CustomerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class NewCustomer {
    private static List<String> errorListPWPW = new ArrayList<String>();
    private static String emailErr, phoneLenErr;
    @FXML
	private TextField userEntry, pwEntry, fNameEntry, lNameEntry, emailEntry, phoneEntry;

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
        userEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        pwEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        emailEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        phoneEntry.setTextFormatter(new TextFormatter<>(phoneFilter));
    }
	
     
    @FXML
	private void submit() throws IOException, SQLException, JDBCException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (isEmptyField()) {
        }
        else if (entryExists()){
        }
        else if (!isFieldValid()){
        }
        else { 
                CustomerDTO newCustomer = new CustomerDTO(userEntry.getText(), pwEntry.getText(), fNameEntry.getText().trim(), 
                                            lNameEntry.getText().trim(), emailEntry.getText().trim(), phoneEntry.getText());  
                CustomerDAOService.saveCustomer(newCustomer);
                notificationWindow("confirmation", null);
                switchToCustomerPg();
            }
        }
	

    private boolean isEmptyField(){
        boolean flag = false;
        if (userEntry.getText().isEmpty() || pwEntry.getText().isEmpty()
            || fNameEntry.getText().trim().isEmpty() || lNameEntry.getText().trim().isEmpty()
            || emailEntry.getText().trim().isEmpty() || phoneEntry.getText().isEmpty()) {
            flag = true;
            
            if (userEntry.getText().isEmpty()){
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
        return flag;
    }

    // checks if duplicate property value exists in DB
    private boolean entryExists(){
        boolean flag = false;
        if (CustomerDAOService.userNameExists(userEntry.getText())){
            notificationWindow("error", "User Name Exists!");
            flag = true;
            }
        else if (CustomerDAOService.emailExists(emailEntry.getText())){
            notificationWindow("error", "Email Exists!");
            flag = true;
            }
        else if (CustomerDAOService.phoneExists(phoneEntry.getText())){
            notificationWindow("error", "Phone Exists!");
            flag = true;
            }
        return flag;
    }

    
    private boolean isFieldValid(){
        boolean flag = true;
        if (!pwValidator(pwEntry, errorListPWPW)){
            notificationWindow("error", stringFormatter(errorListPWPW));
            flag = false;
            }
        else if (!emailValidator(emailEntry)){
            notificationWindow("error", emailErr);
            flag = false;
            }
        else if (!phoneLenValidator(phoneEntry)){
            notificationWindow("error", phoneLenErr);
            flag = false;
            }
        else if (!emailValidator(emailEntry)){
            notificationWindow("error", emailErr);
            flag = false;
            }
        return flag;
    }
    

    private static boolean pwValidator(TextField pwField, List<String> errorListPW) {
        String pw = pwField.getText();
        Pattern specialChar = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern number = Pattern.compile("[0-9]");
        errorListPW.clear();
        boolean flag=true;

        if (pw.length() < 8) {
            errorListPW.add(" Password must be at least 8 Characters!\n");
            flag=false;
        }
        if (!specialChar.matcher(pw).find()) {
            errorListPW.add("Password must have at least one Special Character!\n");
            flag=false;
        }
        if (!upperCase.matcher(pw).find()) {
            errorListPW.add("Password must have at least one Upper Case letter!\n");
            flag=false;
        }
        if (!lowerCase.matcher(pw).find()) {
            errorListPW.add("Password must have at least one Lower Case letter!\n");
            flag=false;
        }
        if (!number.matcher(pw).find()) {
            errorListPW.add("Password must have at least one Number!");
            flag=false;
        }
        return flag;
    }


    // email field accepts only @address...
    private static boolean emailValidator(TextField emailField) {
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


    private static boolean phoneLenValidator(TextField phoneField) {
        String phone = phoneField.getText();
        if (phone.length() != 10) {
            phoneLenErr = "Phone Number must be 10 digits!";
            return false;
        }
        return true;
    }

    // removes [], for user errors
    private String stringFormatter(List lst) {
        String listToStr = Arrays.toString(lst.toArray()).replace("[", "").replace("]", "").replace(",", "");
        return listToStr;
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
    
    // confirmation/error window for user
    private static void notificationWindow(String windowType, String message) {
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
