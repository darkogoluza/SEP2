package client.view.administratorView;

import client.model.ManageProducts;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.objects.Product;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class AdministratorViewModel {

    private ListProperty<String> listViewAdministrator;
	private ManageProducts model;

	public AdministratorViewModel(ManageProducts model) {
		listViewAdministrator = new SimpleListProperty<>();
		this.model = model;

		model.addPropertyChangeListener("productAdded", this::productAdded);
	}

	private void productAdded(PropertyChangeEvent event) {
		listViewAdministrator.set(FXCollections.observableArrayList((ArrayList<String>) event.getNewValue()));
	}

	public void addProduct(Product product) {
		model.add(product);
	}

	public void removeProduct(int id) {
		model.remove(id);
	}

	public ListProperty<String> getListViewAdministrator () {
		return listViewAdministrator;
	}
}
