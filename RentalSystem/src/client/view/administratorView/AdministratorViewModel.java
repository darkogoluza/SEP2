package client.view.administratorView;

import client.model.ModelProxy;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import shared.networking.model.ManageProducts;
import shared.objects.errors.AlertHandler;
import shared.objects.product.*;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * AdministratorViewModel for AdministratorViewController
 */
public class AdministratorViewModel {

	private ListProperty<String> listViewAdministrator;
	private StringProperty size;
	private String image;
	private StringProperty price;
	private StringProperty color;
	private StringProperty type;
	private StringProperty amount;
	private ManageProducts model;
	private ModelProxy modelProxy;

	/**
	 * constructor with Listener for product modification
	 */
	public AdministratorViewModel(ModelProxy modelProxy) {
		listViewAdministrator = new SimpleListProperty<>();
		size = new SimpleStringProperty();
		price = new SimpleStringProperty();
		type = new SimpleStringProperty();
		color = new SimpleStringProperty();
		amount = new SimpleStringProperty();
		this.model = modelProxy.getManageProducts();
		this.modelProxy = modelProxy;

		model.addPropertyChangeListener("productModified", this::productModified);
	}

	/**
	 * Method refresh product specifications on event
	 * @param event
	 */
	private void productModified(PropertyChangeEvent event) {
		listViewAdministrator.set(FXCollections.observableArrayList((ArrayList<String>) event.getNewValue()));
	}

	/**
	 * get all products, convert them to String and put into ArrayList
	 */
	public void loadData() {
		listViewAdministrator.set(
				FXCollections.observableArrayList(model.getAllProducts().convertToStringArrayList()));
	}

	/**
	 * adding new product to database
	 * @param price
	 * @param color
	 * @param equipmentType
	 * @param size
	 * @param amount
	 */
	public void addProduct(double price, Color color, EquipmentType equipmentType, Size size, int amount) {

		if (image == null) {
			AlertHandler.getInstance().wrongFile();
		} else {
			model.add(price, color, equipmentType, size, amount, image);
		}
	}

	/**|
	 * remove product from database
	 * @param index
	 */
	public void removeProduct(int index) {
		model.remove(index);
	}

	/**
	 * clear all fields
	 */
	public void clearFields() {
		size.set("");
		price.set("");
		amount.set("");
	}

	/**
	 * method set new product specifications
	 * @param index
	 * @param price
	 * @param color
	 * @param size
	 * @param amount
	 */
	public void changeProduct(int index, double price, Color color, Size size, int amount) {
		model.changeProduct(index, price, color, size, amount);
	}

	/**
	 *
	 * @param index
	 */

	/**
	 * Method get product by index and set information about product
	 * @param index
	 */
	public void setFieldsTo(int index) {
		Product product = model.getProduct(index);
		size.set(product.getSize().getSizeWithoutUnit());
		price.set(product.getPrice() + "");
		type.set(product.getType().toString());
		color.set(product.getColor().toString());
		amount.set(product.getAmount() + "");
	}

	/**
	 * return list with all administrators
	 */
	public ListProperty<String> getListViewAdministrator() {
		return listViewAdministrator;
	}

	/**
	 * Getter for price
	 * @return
	 */
	public StringProperty getPriceStringProperty() {
		return price;
	}

	/**
	 * Getter for size
	 * @return
	 */
	public StringProperty getSizeStringProperty() {
		return size;
	}

	/**
	 * Getter for type
	 * @return
	 */
	public StringProperty getType() {
		return type;
	}

	/**
	 * Getter for color
	 * @return
	 */
	public StringProperty getColor() {
		return color;
	}

	/**
	 * log out user from app
	 */
	public void logOff() {
		modelProxy.getManageUser().logout();
	}

	/**
	 * Getter for product amount
	 * @return amount
	 */
	public StringProperty getAmountStringProperty() {
		return amount;
	}

	/**
	 * add image as file
	 * @param file
	 */
	public void addFile(File file) {
		if (file != null) {
			String imagePath = file.getPath();

			this.image = imagePath;
		}
		else {
			AlertHandler.getInstance().wrongFile();
		}
	}
}
