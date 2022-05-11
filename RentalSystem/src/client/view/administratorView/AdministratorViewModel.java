package client.view.administratorView;

import client.model.ModelProxy;
import client.model.product.ManageProducts;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import shared.objects.product.Color;
import shared.objects.product.EquipmentType;
import shared.objects.product.Product;
import shared.objects.product.Size;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class AdministratorViewModel {

	private ListProperty<String> listViewAdministrator;
	private StringProperty size;
	private StringProperty price;
	private StringProperty color;
	private StringProperty type;
	private ManageProducts model;

	public AdministratorViewModel(ModelProxy modelProxy) {
		listViewAdministrator = new SimpleListProperty<>();
		size = new SimpleStringProperty();
		price = new SimpleStringProperty();
		type = new SimpleStringProperty();
		color = new SimpleStringProperty();
		this.model = modelProxy.getManageProducts();

		model.addPropertyChangeListener("productModified", this::productModified);
	}

	private void productModified(PropertyChangeEvent event) {
		listViewAdministrator.set(FXCollections.observableArrayList((ArrayList<String>) event.getNewValue()));
	}

	public void loadData() {
		listViewAdministrator.set(FXCollections.observableArrayList(model.getAllProducts().convertToStringArrayList()));
	}

	public void addProduct(double price, Color color, EquipmentType equipmentType, Size size) {

		model.add(price, color, equipmentType, size);
	}

	public void removeProduct(int index) {
		model.remove(index);
	}

	public void clearFields() {
		size.set("");
		price.set("");
	}

	public void changeProduct(int index, double price, Color color, Size size) {
		model.changeProduct(index, price, color, size);
	}

	public void setFieldsTo(int index) {
		Product product = model.getProduct(index);
		size.set(product.getSize().toString());
		price.set(product.getPrice() + "");
		type.set(product.getType().toString());
		color.set(product.getColor().toString());
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
}