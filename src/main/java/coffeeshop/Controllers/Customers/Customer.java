package coffeeshop.Controllers.Customers;

import java.io.IOException;
import coffeeshop.App;
import coffeeshop.Data.CoffeeList;
import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Coffee.CoffeeDAOService;
import javafx.fxml.FXML;


public class Customer {
    public void initialize() {
        saveCoffeeToDB();
    }

    //saves coffee constants to DB upon first program start only
	private void saveCoffeeToDB() {
		if (!CoffeeDAOService.coffeeListExistsDB()) {
			for (Coffee coffee : CoffeeList.getCoffeeList()) {
				CoffeeDAOService.saveCoffeeTableToDB(coffee);
			}
		}
	}

    @FXML
    private void switchToCustomerNewOrderPg() throws IOException {
        App.setRoot("customerNewOrder");
    }
    @FXML
    private void switchToCustomerOrdersPg() throws IOException {
        App.setRoot("customerOrders");
    }
    @FXML
    private void switchToCustomerProfilePg() throws IOException {
        App.setRoot("customerProfile");
    }
    @FXML
    private void switchToLoginPg() throws IOException {
        App.setRoot("customerLogin");
    }
    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
}
