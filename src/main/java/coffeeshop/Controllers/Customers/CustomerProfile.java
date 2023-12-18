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
import coffeeshop.Models.Context;
import coffeeshop.Security.PasswordGen;
import coffeeshop.Entities.Customers.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class CustomerProfile {
    List<String> errorList = new ArrayList<String>();
    static String emailErr;
    static String phoneLenErr;
    @FXML
    private static Customer customer;
    @FXML
	private Customer data;
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
    public void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException{
        userEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        pwEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        emailEntry.setTextFormatter(new TextFormatter<>(spaceFilter));
        phoneEntry.setTextFormatter(new TextFormatter<>(phoneFilter));
        customer = Context.getInstance().getCustomer();
        data = new Customer(customer.getUserName(), customer.getPassword(), customer.getFirstName(),
														customer.getLastName(), customer.getEmail(), customer.getPhone());               
        loadData();
    }


    private void loadData() {
        userEntry.setText(data.getUserName());
        pwEntry.setText("Enter New Password");
        fNameEntry.setText(data.getFirstName());
        lNameEntry.setText(data.getLastName());
        emailEntry.setText(data.getEmail());
        phoneEntry.setText(data.getPhone());
    }
	
     
    @FXML
	private void edit(ActionEvent event) throws IOException, SQLException, JDBCException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (userEntry.getText().equals(data.getUserName()) && pwEntry.getText().equals("")
            && fNameEntry.getText().equals(data.getFirstName()) && lNameEntry.getText().equals(data.getLastName()) 
            && emailEntry.getText().equals(data.getEmail()) && phoneEntry.getText().equals(data.getPhone()) ) {
                    notificationWindow("error", "No Changes Made!");
        }
        else if (userEntry.getText().equals(data.getUserName()) && pwEntry.getText().equals("Enter New Password")
            && fNameEntry.getText().equals(data.getFirstName()) && lNameEntry.getText().equals(data.getLastName()) 
            && emailEntry.getText().equals(data.getEmail()) && phoneEntry.getText().equals(data.getPhone()) ) {
                    notificationWindow("error", "No Changes Made!");
        }
        else if (userEntry.getText().isEmpty() ||  pwEntry.getText().isEmpty()
            || fNameEntry.getText().trim().isEmpty() || lNameEntry.getText().trim().isEmpty()
            || emailEntry.getText().isEmpty() || phoneEntry.getText().isEmpty()) {
        
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
            else if (emailEntry.getText().isEmpty()){
            notificationWindow("error", "Email Empty!");
            }
            else if (phoneEntry.getText().isEmpty()){
                notificationWindow("error", "Phone Empty!");
            }
        }
        else {
            if (!data.getUserName().equals(userEntry.getText()) && CustomerDAOService.userNameExists(userEntry.getText())){
                notificationWindow("error", "User Name Exists!");
                }
            else if (!data.getEmail().equals(emailEntry.getText()) && CustomerDAOService.emailExists(emailEntry.getText())){
                notificationWindow("error", "Email Exists!");
                }
            else if (!data.getPhone().equals(phoneEntry.getText()) && CustomerDAOService.phoneExists(phoneEntry.getText())){
                notificationWindow("error", "Phone Exists!");
                }
            if (!pwValidator(pwEntry, errorList) && !pwEntry.getText().equals("Enter New Password")){
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
                customer.setUserName(userEntry.getText());
                if (!pwEntry.getText().equals("Enter New Password")) {
                    customer.setPassword(PasswordGen.getHashedPw(pwEntry.getText()));
                }
                customer.setFirstName(fNameEntry.getText());
                customer.setLastName(lNameEntry.getText());
                customer.setEmail(emailEntry.getText());
                customer.setPhone(phoneEntry.getText());
                CustomerDAOService.editCustomer();
                notificationWindow("confirmation", null);
                switchToCustomerPg();
            }
        }
	}
    

    private static boolean pwValidator(TextField pwField, List<String> errorList) {
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


    private String stringFormatter(List lst) {
        String listToStr = Arrays.toString(lst.toArray()).replace("[", "").replace("]", "").replace(",", "");
        return listToStr;
    }

    @FXML
    private void pwFieldReset() {
        pwEntry.clear();
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
            dialog = new Alert(AlertType.CONFIRMATION, "Successfully Edited!", ButtonType.OK);
        }
        else {
            dialog = new Alert(AlertType.ERROR, message, ButtonType.OK);
        }
        dialog.show();
    }
}
