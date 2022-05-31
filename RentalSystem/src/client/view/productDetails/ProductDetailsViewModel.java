package client.view.productDetails;

import client.model.ModelProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
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

/**
 * ProductDetailsViewModel for ProductDetailsController
 */
public class ProductDetailsViewModel {
	private SimpleStringProperty type;
	private SimpleStringProperty size;
	private SimpleStringProperty price;
	private SimpleStringProperty username;
	private SimpleStringProperty color;
	private SimpleStringProperty name;
	private SimpleStringProperty amountInStock;
	private SimpleStringProperty amountInBasket;

	private java.awt.Image image;

	private ModelProxy modelProxy;
	private int id;
	private Product product;

	/**
	 * constructor communicate with modelProxy and its getting fields values for single product view
	 * @param modelProxy
	 */
	public ProductDetailsViewModel(ModelProxy modelProxy) {
		this.modelProxy = modelProxy;
		initializeItems();

		modelProxy.getManageBasket().addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
	}

	/**
	 * after event is fired automatically change amount in basket
	 * @param propertyChangeEvent
	 */
	private void modifiedBasket(PropertyChangeEvent propertyChangeEvent) {
		amountInBasket.setValue(String.valueOf(propertyChangeEvent.getNewValue()));
	}

	/**
	 * get product with given id
	 */
	public void setProduct() {
		product = modelProxy.getManageProducts().getProduct(id);
		loadItems();
	}

	/**
	 * Getter for image
	 * @return
	 */
	public javafx.scene.image.Image getImage() {
		try {
			return createImage(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * log out user
	 */
	public void logOff() {
		modelProxy.getManageUser().logout();
	}

	/**
	 * adds product to basket
	 */
	public void addToBasket() {
		modelProxy.getManageBasket().add(product);
	}

	/**
	 * initialization of all product fields
	 */
	private void initializeItems() {
		size = new SimpleStringProperty();
		type = new SimpleStringProperty();
		price = new SimpleStringProperty();
		name = new SimpleStringProperty();
		color = new SimpleStringProperty();
		username = new SimpleStringProperty();
		amountInStock = new SimpleStringProperty();
		amountInBasket = new SimpleStringProperty();
	}

	/**
	 * load all product's fields
	 */
	private void loadItems() {
		username.setValue(modelProxy.getManageUser().getLoggedUser().getUsername());
		size.setValue(product.getSize().toString());
		type.setValue(product.getType().toString());
		price.setValue(String.valueOf(product.getPrice()));
		name.setValue(String.valueOf(product.getType()));
		color.setValue(String.valueOf(product.getColor()));
		amountInStock.setValue(String.valueOf(product.getAmount()));
		amountInBasket.setValue(String.valueOf(modelProxy.getManageBasket().size()));

		byte[] imgBytes = modelProxy.getManageProducts().getImage(id);
		image = new ImageIcon(imgBytes).getImage();
	}

	/**
	 * Setter for id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * creates image
	 * @param image
	 * @return
	 * @throws IOException
	 */
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

	public SimpleStringProperty typeProperty() {
		return type;
	}
	public SimpleStringProperty sizeProperty() {
		return size;
	}
	public SimpleStringProperty priceProperty() {
		return price;
	}
	public SimpleStringProperty colorProperty() {
		return color;
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
