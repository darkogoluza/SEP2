package client.model.product;

import client.model.product.ManageProductDatabase;
import client.model.product.ManageProducts;
import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.product.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ManageProductsManager implements ManageProducts {
	private ProductList list;
	private PropertyChangeSupport changeSupport;
	private ManageProductDatabase manageProductDatabase;

	public ManageProductsManager() {
		list = new ProductList();
		changeSupport = new PropertyChangeSupport(this);
		try {
			manageProductDatabase = new ManageProductDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			list = manageProductDatabase.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add product to list
	 * @param price
	 * @param color
	 * @param equipmentType
	 * @param size
	 */
	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size) {
		Product product = list.add(price, color, equipmentType, size);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
		try {
			manageProductDatabase.save(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Remove selected item with index from list
	 * @param index of product
	 */
	@Override
	public void remove(int index) {
		Product product = list.removeByIndex(index);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
		try {
			manageProductDatabase.remove(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}

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

	@Override
	public ProductList getAllProducts() {
		return list;
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
		Product product = list.get(index);
		product.setPrice(newPrice);
		product.setColor(newColor);
		product.setSize(newSize);
		try {
			manageProductDatabase.change(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		list.change(index, newPrice, newColor, newSize);
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
