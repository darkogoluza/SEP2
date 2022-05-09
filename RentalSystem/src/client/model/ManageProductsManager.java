package client.model;

import shared.objects.Product;

import java.util.ArrayList;

public class ManageProductsManager implements ManageProducts {

	private ArrayList<Product> list;
	private PropertyChangeSupport changeSupport;

	public ManageProductsManager() {
		list = new ArrayList<>();
		changeSupport = new PropertyChangeSupport(this);
	}

	@Override
	public void add(Product product) {
		list.add(product);
		changeSupport.firePropertyChange("productAdded", null, getAllProducts());
	}

	@Override
	public void remove(int id) {
		for (Product product :
				list) {
			// TODO: 02/05/2022 product id is String or int?
			if (product.getId() == id) {
				list.remove(product);
			}
		}
	}

	private ArrayList<String> getAllProducts() {
		ArrayList<String> temp = new ArrayList<>();
		for (Product product : list) {
			temp.add(product.toString());
		}

		return temp;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(name, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(name, listener);
	@Override
	public void showAllProducts() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
		}
	}
}
