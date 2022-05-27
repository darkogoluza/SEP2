package client.view.productDetails;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductDetailsController {
	@FXML
	private Label amountInStock;
	@FXML
	private Label color;
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

	public void init(ViewHandler viewHandler, ViewModelFactory vmf, int id) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getProductDetailsViewModel();

		bindItems();

		viewModel.setId(id);
		viewModel.setProduct();
		imageView.setImage(viewModel.getImage());
	}

	public void backButton() {
		viewHandler.openCustomerAllEquipmentView();
	}

	public void onLogOff() {
		viewModel.logOff();
		viewHandler.openLoginView();
	}

	public void onGoToReservations() {
		viewHandler.openCustomerAllOrdersView();
	}

	public void addToBasket() {
		viewModel.addToBasket();
	}

	public void onGoToBasketButton() {
		viewHandler.openCustomerBasket();
	}

	private void bindItems() {
		userName.textProperty().bindBidirectional(viewModel.usernameProperty());
		size.textProperty().bindBidirectional(viewModel.sizeProperty());
		color.textProperty().bindBidirectional(viewModel.colorProperty());
		name.textProperty().bindBidirectional(viewModel.nameProperty());
		price.textProperty().bindBidirectional(viewModel.priceProperty());
		amountInStock.textProperty().bindBidirectional(viewModel.amountInStockProperty());
		totalItemsInBasket.textProperty().bindBidirectional(viewModel.amountInBasketProperty());
	}
}
