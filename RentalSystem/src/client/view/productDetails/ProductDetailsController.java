package client.view.productDetails;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.registryView.RegistryViewModel;
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

		imageView.setImage(viewModel.getImage());
	}


}
