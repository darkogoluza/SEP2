package shared.objects.errors;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import shared.objects.product.Product;

import java.util.Optional;

public class AlertHandler {

	private static AlertHandler instance;
	private static Object lock = new Object();

//	private Alert.AlertType type;
//	private String header;
//	private String body;


	private AlertHandler() {

	}

	public static AlertHandler getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new AlertHandler();
				}
			}
		}
		return instance;
	}

	public void administratorWrongInput() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("You must have entered wrong input or one field is left empty");
		alert.setContentText("""
				Price needs to be a number.
				Label size need to be one of ["S","M","L","XL","XXL","XXXL"]
				Height size must be a number.
				Label for shoes is a number between 35 and 45.
				Amount must be a number\s""");

		alert.showAndWait();
	}

	public void emptyBasket() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("First you must select witch equipment to reserve!");

		alert.showAndWait();
	}

	public void wrongCredentials() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Combination of this username and password is wrong!");

		alert.showAndWait();
	}

	public void userExists() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("This username is already taken. Please select different username!");

		alert.showAndWait();
	}

	public void passwordsDontMatch() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Those passwords don’t match. Please try again!");

		alert.showAndWait();
	}

	public void orderCreated() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText("Order was successfully created!");

		alert.showAndWait();
	}

	public void orderDontExist() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Order with this id don't exist!");

		alert.showAndWait();
	}

    public boolean onRemoveReservation(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Confirm that you want to remove this reservation, you won't be able to change this decision later!");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean onRemoveProduct() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Confirm that you want to remove this product, you won't be able to change this decision later!");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
			return false;
		}
		else {
			return true;
		}
	}

	public void wrongOrderIdInput() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Order ID can be only number!");

		alert.showAndWait();
	}

    public void onOrderExpireSoon(int id, int hoursBeforeExpiration) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(String.format("Order with id(%d) will expire in %dh pleas make sure to return the equipment in time", id, hoursBeforeExpiration));

		alert.showAndWait();
    }

	public void onProductOutOfStock(Product product) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(String.format("Product %s %s %.2f€ is out of stock and can not be put to basket", product.getColor(), product.getType(), product.getPrice()));

		alert.showAndWait();
	}
}