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

	private void productModified(PropertyChangeEvent event) {
		listViewAdministrator.set(FXCollections.observableArrayList((ArrayList<String>) event.getNewValue()));
	}

	public void loadData() {
		listViewAdministrator.set(
				FXCollections.observableArrayList(model.getAllProducts().convertToStringArrayList()));
	}

	public void addProduct(double price, Color color, EquipmentType equipmentType, Size size, int amount) {

		if (image == null) {
			AlertHandler.getInstance().wrongFile();
		} else {
			model.add(price, color, equipmentType, size, amount, image);
		}
	}

	public void removeProduct(int index) {
		model.remove(index);
	}

	public void clearFields() {
		size.set("");
		price.set("");
		amount.set("");
	}

	public void changeProduct(int index, double price, Color color, Size size, int amount) {
		model.changeProduct(index, price, color, size, amount);
	}

	public void setFieldsTo(int index) {
		Product product = model.getProduct(index);
		size.set(product.getSize().getSizeWithoutUnit());
		price.set(product.getPrice() + "");
		type.set(product.getType().toString());
		color.set(product.getColor().toString());
		amount.set(product.getAmount() + "");
	}

	public ListProperty<String> getListViewAdministrator() {
		return listViewAdministrator;
	}

	public StringProperty getPriceStringProperty() {
		return price;
	}

	public StringProperty getSizeStringProperty() {
		return size;
	}

	public StringProperty getType() {
		return type;
	}

	public StringProperty getColor() {
		return color;
	}

	public void logOff() {
		modelProxy.getManageUser().logout();
	}

	public StringProperty getAmountStringProperty() {
		return amount;
	}

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
