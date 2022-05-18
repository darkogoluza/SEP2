package client.model.product;

import client.networking.ClientProxy;
import server.model.product.ManageProductDatabase;
import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.product.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ManageProductsManager implements ManageProducts {
	private ProductList list;
	private PropertyChangeSupport changeSupport;
	private ClientProxy clientProxy;

	public ManageProductsManager(ClientProxy clientProxy) {
		list = new ProductList();
		changeSupport = new PropertyChangeSupport(this);
		this.clientProxy = clientProxy;
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
		list.add(price, color, equipmentType, size);
		clientProxy.getClientProduct().add(price, color, equipmentType, size);

		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
	}

	/**
	 * Remove selected item with index from list
	 * @param index of product
	 */
	@Override
	public void remove(int index) {
		Product product = list.removeByIndex(index);
		clientProxy.getClientProduct().remove(index);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
	}

	/**
	 * Get product from database
	 * @param id of product
	 * @return Product
	 */
	@Override
	public Product getProduct(int id) {
		return clientProxy.getClientProduct().getProduct(id);
	}

	@Override
	public ProductList getAllProducts() {
		return clientProxy.getClientProduct().getAllProducts();
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
		clientProxy.getClientProduct().changeProduct(index, newPrice, newColor, newSize);
		changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
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
