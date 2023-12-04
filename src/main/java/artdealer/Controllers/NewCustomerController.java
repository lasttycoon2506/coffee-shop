package artdealer.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
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
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
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
	void submit(ActionEvent event) throws IOException, SQLException {
        if (firstNameEntry.getText().trim().isEmpty() || lastNameEntry.getText().trim().isEmpty()
            || emailEntry.getText().trim().isEmpty() || phoneEntry.getText().trim().isEmpty()) {
            
            if (firstNameEntry.getText().trim().isEmpty()){
                fNameLabel.setTextFill(Color.color(1, 0, 0));
                fNameLabel.setText("Enter First Name!");
            }
            if (lastNameEntry.getText().trim().isEmpty()){
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
            CustomerDTO newEntry = new CustomerDTO(firstNameEntry.getText(), lastNameEntry.getText(), emailEntry.getText(), phoneEntry.getText());
            System.out.println(newEntry);
            createDB.createDB();
            insertDB.InsertCustomerData(newEntry);
            switchToCustomerRegisteredPg();
        }
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
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }

    @FXML
    private void switchToCustomerRegisteredPg() throws IOException {
        App.setRoot("newCustomerRegistered");
    }
}
