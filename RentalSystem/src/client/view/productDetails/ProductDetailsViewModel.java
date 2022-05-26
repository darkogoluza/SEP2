package client.view.productDetails;

import client.model.ModelProxy;
import javafx.scene.image.Image;

public class ProductDetailsViewModel {
	private ModelProxy modelProxy;

	private Image image;

	public ProductDetailsViewModel(ModelProxy modelProxy) {
		this.modelProxy = modelProxy;
		image = new Image("/image/profile.png");
	}

	public Image getImage() {
		return image;
	}
}
