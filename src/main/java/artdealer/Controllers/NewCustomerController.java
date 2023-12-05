package artdealer.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import artdealer.App;
import artdealer.Models.CustomerDTO;
import artdealer.SQL.CreateDB;
import artdealer.SQL.InsertDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.StringConverter;


public class NewCustomerController {
    CreateDB createDB = new CreateDB();
    InsertDB insertDB = new InsertDB();
    
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

    // phone validator
    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?([1-9][0-9]*)?")) {
            return change;
        }
        return null;
    };
    StringConverter<Integer> converter = new IntegerStringConverter() {
        @Override
        public Integer fromString(String s) {
            if (s.isEmpty()) return 0;
            return super.fromString(s);
        }
    };
    public void initialize(){
        phoneEntry.setTextFormatter(new TextFormatter<Integer>(converter, null, integerFilter));
    }
	
     
    @FXML
	public void submit(ActionEvent event) throws IOException, SQLException {
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
            CustomerDTO newEntry = new CustomerDTO(fNameEntry.getText(), lNameEntry.getText(), emailEntry.getText(), phoneEntry.getText());
            createDB.createDB();
            insertDB.InsertCustomerData(newEntry);
            switchToCustomerRegisteredPg();
        }
	}

    public static boolean isValid(String passwordhere, String confirmhere, List<String> errorList) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        errorList.clear();

        boolean flag=true;

        if (!passwordhere.equals(confirmhere)) {
            errorList.add("password and confirm password does not match");
            flag=false;
        }
        if (passwordhere.length() < 8) {
            errorList.add("Password lenght must have alleast 8 character !!");
            flag=false;
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one specail character !!");
            flag=false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one uppercase character !!");
            flag=false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one lowercase character !!");
            flag=false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one digit character !!");
            flag=false;
        }
        return flag;
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
