package client.model;

import shared.objects.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageProductsManager implements ManageProducts {
	private ProductArrayList list;
	private PropertyChangeSupport changeSupport;

	public ManageProductsManager() {
		list = new ProductArrayList();
		changeSupport = new PropertyChangeSupport(this);
	}

	/**
	 * Add product to list @todo change this for database when implemented
	 * @param price
	 * @param color
	 * @param equipmentType
	 * @param size
	 */
	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size) {
		list.add(price, color, equipmentType, size);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
	}

	/**
	 * Remove selected item with index from list @todo change this for database when implemented
	 * @param index of product
	 */
	@Override
	public void remove(int index) {
		list.removeByIndex(index);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
	}

	/**
	 * Get product from list @todo change this for database when implemented
	 * @param index of product
	 * @return Product
	 */
	@Override
	public Product getProduct(int index) {
		return list.getByIndex(index);
	}

	/**
	 * Update price, color and size of selected product using index of product
	 * @param index
	 * @param newPrice
	 * @param newColor
	 * @param newSize
	 */
	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize) {
		list.change(index, newPrice, newColor, newSize);
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
