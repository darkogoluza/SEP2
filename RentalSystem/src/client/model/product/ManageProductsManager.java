package client.model.product;

import client.networking.ClientProxy;
import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.product.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageProductsManager implements ManageProducts {
	private PropertyChangeSupport changeSupport;
	private ClientProxy clientProxy;

	public ManageProductsManager(ClientProxy clientProxy) {
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
		clientProxy.getClientProduct().add(price, color, equipmentType, size);

		changeSupport.firePropertyChange("productModified", null, clientProxy.getClientProduct().convertToStringArrayList());
	}

	/**
	 * Remove selected item with index from list
	 * @param index of product
	 */
	@Override
	public void remove(int index) {
		clientProxy.getClientProduct().remove(index);
		changeSupport.firePropertyChange("productModified", null, clientProxy.getClientProduct().convertToStringArrayList());
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
		changeSupport.firePropertyChange("productModified", null, clientProxy.getClientProduct().convertToStringArrayList());
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
