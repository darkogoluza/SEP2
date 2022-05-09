package client.view.administratorView;

import client.model.ManageProducts;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import shared.objects.Color;
import shared.objects.EquipmentType;
import shared.objects.Size;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class AdministratorViewModel {

	private ListProperty<String> listViewAdministrator;
	private StringProperty size;
	private StringProperty price;
	private ManageProducts model;

	public AdministratorViewModel(ManageProducts model) {
		listViewAdministrator = new SimpleListProperty<>();
		size = new SimpleStringProperty();
		price = new SimpleStringProperty();
		this.model = model;

		model.addPropertyChangeListener("productModified", this::productModified);
	}

	private void productModified(PropertyChangeEvent event) {
		listViewAdministrator.set(FXCollections.observableArrayList((ArrayList<String>) event.getNewValue()));
	}

	public void addProduct(double price, Color color, EquipmentType equipmentType, Size size) {

		model.add(price, color, equipmentType, size);
	}

	public void removeProduct(int id) {
		model.remove(id);
	}

	public void clearFields() {

		size.set("");
		price.set("");
	}

	public void setFieldsTo() {

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
}