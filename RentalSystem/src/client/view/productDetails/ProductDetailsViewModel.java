package client.view.productDetails;

import client.model.ModelProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import shared.objects.errors.AlertHandler;
import shared.objects.product.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProductDetailsViewModel {
	private SimpleStringProperty size;
	private SimpleStringProperty price;
	private SimpleStringProperty username;
	private SimpleStringProperty name;
	private SimpleStringProperty amountInStock;
	private SimpleStringProperty amountInBasket;

	private java.awt.Image image;

	private ModelProxy modelProxy;
	private int id;
	private Product product;

	public ProductDetailsViewModel(ModelProxy modelProxy) {
		this.modelProxy = modelProxy;
		initializeItems();

		modelProxy.getManageBasket().addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
	}

	private void modifiedBasket(PropertyChangeEvent propertyChangeEvent) {
		amountInBasket.setValue(String.valueOf(propertyChangeEvent.getNewValue()));
	}

	public void setProduct() {
		product = modelProxy.getManageProducts().getProduct(id);
		loadItems();
	}

	public javafx.scene.image.Image getImage() {
		try {
			return createImage(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void logOff() {
		modelProxy.getManageUser().logout();
	}

	public void addToBasket() {
		if (modelProxy.getManageBasket().checkIfProductIsInStock(id)) {
			modelProxy.getManageBasket().add(product);
		}
		else {
			AlertHandler.getInstance().onProductOutOfStock(modelProxy.getManageProducts().getProduct(id));
		}
	}


	private void initializeItems() {
		size = new SimpleStringProperty();
		price = new SimpleStringProperty();
		name = new SimpleStringProperty();
		username = new SimpleStringProperty();
		amountInStock = new SimpleStringProperty();
		amountInBasket = new SimpleStringProperty();
	}

	private void loadItems() {
		username.setValue(modelProxy.getManageUser().getLoggedUser().getUsername());
		size.setValue(product.getSize().toString());
		price.setValue(String.valueOf(product.getPrice()));
		name.setValue(String.valueOf(product.getType()));
		amountInStock.setValue(String.valueOf(product.getAmount()));
		amountInBasket.setValue(String.valueOf(modelProxy.getManageBasket().size()));

		byte[] imgBytes = modelProxy.getManageProducts().getImage(id);
		image = new ImageIcon(imgBytes).getImage();
	}

	public void setId(int id) {
		this.id = id;
	}

	private static javafx.scene.image.Image createImage(java.awt.Image image) throws IOException {
		if (!(image instanceof RenderedImage)) {
			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();

			image = bufferedImage;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write((RenderedImage) image, "png", out);
		out.flush();
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		return new javafx.scene.image.Image(in);
	}

	public SimpleStringProperty sizeProperty() {
		return size;
	}
	public SimpleStringProperty priceProperty() {
		return price;
	}
	public SimpleStringProperty usernameProperty() {
		return username;
	}
	public SimpleStringProperty nameProperty() {
		return name;
	}

	public SimpleStringProperty amountInStockProperty() {
		return amountInStock;
	}

	public SimpleStringProperty amountInBasketProperty() {
		return amountInBasket;
	}
}
