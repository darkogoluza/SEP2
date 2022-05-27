package client.view.productDetails;

import client.model.ModelProxy;
import javafx.beans.property.SimpleStringProperty;
import shared.objects.product.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProductDetailsViewModel {
	private SimpleStringProperty type;
	private SimpleStringProperty size;
	private SimpleStringProperty price;
	private java.awt.Image image;

	private ModelProxy modelProxy;
	private int id;
	private Product product;

	public ProductDetailsViewModel(ModelProxy modelProxy) {
		this.modelProxy = modelProxy;
		initializeItems();
	}

	public void setProduct() {
		product = modelProxy.getManageProducts().getProduct(id);
		loadItems();
	}

	private void initializeItems() {
		size = new SimpleStringProperty();
		type = new SimpleStringProperty();
		price = new SimpleStringProperty();
	}

	private void loadItems() {
		size.setValue(product.getSize().toString());
		type.setValue(product.getType().toString());
		price.setValue(String.valueOf(product.getPrice()));
		byte[] imgBytes = modelProxy.getManageProducts().getImage(id);
		image = new ImageIcon(imgBytes).getImage();

	}

	public void setId(int id) {
		this.id = id;
	}

	public javafx.scene.image.Image getImage() {
		try {
			return createImage(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static javafx.scene.image.Image createImage(java.awt.Image image) throws IOException {
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
}
