package shared.objects.errors;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import shared.objects.product.Product;

import java.util.Optional;

/**
 * Singleton that handles all kind of alert pop-ups.
 */
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

	/**
	 * Called when administrator enters wrong input while creating product.
	 */
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

	/**
	 * Called when customer tries to other something without products in basket.
	 */
	public void emptyBasket() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("First you must select witch equipment to reserve!");

		alert.showAndWait();
	}

	/**
	 * Called when entering wrong username or password while trying to log in.
	 */
	public void wrongCredentials() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Combination of this username and password is wrong!");

		alert.showAndWait();
	}

	/**
	 * Called when trying to create new User with already existing username in database.
	 */
	public void userExists() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("This username is already taken. Please select different username!");

		alert.showAndWait();
	}

	/**
	 * Called when trying to create new User but confirmation password does not match.
	 */
	public void passwordsDoNotMatch() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Those passwords don’t match. Please try again!");

		alert.showAndWait();
	}

	/**
	 * Called when Order is successfully created.
	 */
	public void orderCreated() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText("Order was successfully created!");

		alert.showAndWait();
	}

	/**
	 * Called when trying to access Order by ID that does not match.
	 */
	public void orderDoNotExist() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Order with this id don't exist!");

		alert.showAndWait();
	}

	/**
	 * Called when trying to remove Reservation.
	 * Pop-up shows two options, depending on what option is chosen method returns boolean.
	 * If cancel is chosen method returns false and Reservation would not be removed.
	 * If ok is chosen method returns true and Reservation is removed.
	 * @param event
	 * @return
	 */
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

	/**
	 * Called when trying to remove Product.
	 * Pop-up shows two options, depending on what option is chosen method returns boolean.
	 * If cancel is chosen method returns false and Product would not be removed.
	 * If ok is chosen method returns true and Product is removed.
	 * @return
	 */
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

	/**
	 * Called when employee enters a letter in to ID input field.
	 */
	public void wrongOrderIdInput() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Order ID can be only number!");

		alert.showAndWait();
	}

	/**
	 * Called when customers order will expire soon.
	 * @param id ID of the order that will expire soon.
	 * @param hoursBeforeExpiration How many hours are left before order will expire.
	 */
    public void onOrderExpireSoon(int id, int hoursBeforeExpiration) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(String.format("Order with id(%d) will expire in %dh pleas make sure to return the equipment in time", id, hoursBeforeExpiration));

		alert.showAndWait();
    }

	/**
	 * Called when customers try to add a product to basket which is out of stock.
	 * @param product Details about the product that is out of stock.
	 */
	public void onProductOutOfStock(Product product) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(String.format("Product %s %s %.2f€ is out of stock and can not be put to basket", product.getColor(), product.getType(), product.getPrice()));

		alert.showAndWait();
	}

    public void wrongFile() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("You did not select any file!");

		alert.showAndWait();
    }
}
