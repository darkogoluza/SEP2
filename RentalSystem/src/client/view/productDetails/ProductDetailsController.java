package client.view.productDetails;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ProductDetailsController {
	@FXML
	private ImageView imageView;

	private ViewHandler viewHandler;
	private ProductDetailsViewModel viewModel;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getProductDetailsViewModel();

		viewModel.setId(0);
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
		//@todo
//		viewHandler.openCustomerAllOrdersView();
	}

	public void addToBasket() {
		viewModel.addToBasket();
	}

	public void onGoToBasketButton() {
		viewHandler.openCustomerBasket();
	}

}
