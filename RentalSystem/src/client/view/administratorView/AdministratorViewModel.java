package client.view.administratorView;

import client.model.ManageProducts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.Product;

public class AdministratorViewModel {

    private ObservableList<String> listViewAdministrator;
    private StringProperty idTextFieldAdministrator;
	private ManageProducts model;

	public AdministratorViewModel(ManageProducts model) {
		idTextFieldAdministrator = new SimpleStringProperty();
		listViewAdministrator = FXCollections.observableArrayList();
		this.model = model;
	}

	public void addProduct(Product product) {
		model.add(product);
	}

	public void removeProduct(int id) {
		model.remove(id);
	}

	public StringProperty getIdTextFieldAdministrator() {
		return idTextFieldAdministrator;
	}

	public ObservableList getListViewAdministrator () {
		return listViewAdministrator;
	}

}
