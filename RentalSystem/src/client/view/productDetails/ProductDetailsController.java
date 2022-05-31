package client.view.productDetails;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 Controller for product details view
 */
public class ProductDetailsController {
	@FXML
	private Label amountInStock;
	@FXML
	private ImageView imageView;
	@FXML
	private Label name;
	@FXML
	private Label price;
	@FXML
	private Label size;
	@FXML
	private Label totalItemsInBasket;
	@FXML
	private Label userName;

	private ViewHandler viewHandler;
	private ProductDetailsViewModel viewModel;

	/**
	 * constructor for showing single order view based on product id
	 * @param viewHandler
	 * @param vmf
	 * @param id
	 */
	public void init(ViewHandler viewHandler, ViewModelFactory vmf, int id) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getProductDetailsViewModel();

		bindItems();

		viewModel.setId(id);
		viewModel.setProduct();
		imageView.setImage(viewModel.getImage());
	}

	/**
	 * Opens CustomerAllEquipmentView
	 */
	public void backButton() {
		viewHandler.openCustomerAllEquipmentView();
	}

	/**
	 * user logout
	 */
	public void onLogOff() {
		viewModel.logOff();
		viewHandler.openLoginView();
	}

	/**
	 * open CustomerAllOrdersView
	 */
	public void onGoToReservations() {
		viewHandler.openCustomerAllOrdersView();
	}

	/**
	 * adds product to basket
	 */
	public void addToBasket() {
		viewModel.addToBasket();
	}

	/**
	 * open customer's basket view
	 */
	public void onGoToBasketButton() {
		viewHandler.openCustomerBasket();
	}

	/**
	 * bind all fields from product through viewModel
	 */
	private void bindItems() {
		userName.textProperty().bindBidirectional(viewModel.usernameProperty());
		size.textProperty().bindBidirectional(viewModel.sizeProperty());
		name.textProperty().bindBidirectional(viewModel.nameProperty());
		price.textProperty().bindBidirectional(viewModel.priceProperty());
		amountInStock.textProperty().bindBidirectional(viewModel.amountInStockProperty());
		totalItemsInBasket.textProperty().bindBidirectional(viewModel.amountInBasketProperty());
	}
}
