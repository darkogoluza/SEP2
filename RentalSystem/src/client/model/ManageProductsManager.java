package client.model;

import shared.objects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ManageProductsManager implements ManageProducts {
	private ProductArrayList list;
	private PropertyChangeSupport changeSupport;

	public ManageProductsManager() {
		list = new ProductArrayList();
		changeSupport = new PropertyChangeSupport(this);
	}

	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size) {
		list.add(price, color, equipmentType, size);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
	}

	@Override
	public void remove(int index) {
		list.removeByIndex(index);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
	}

	@Override
	public void showAllProducts() {

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
	}
}
