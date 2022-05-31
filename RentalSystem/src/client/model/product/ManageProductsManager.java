package client.model.product;

import client.networking.ClientProxy;
import javafx.collections.ObservableList;
import shared.networking.model.ManageProducts;
import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.product.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.rmi.RemoteException;

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
	 * @param amount
	 * @param file
	 */
	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size, int amount, String file) {
		clientProxy.getClientProduct().add(price, color, equipmentType, size, amount, file);

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

	/**
	 * Get list of products
	 * @return All Products
	 */
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
	 * @param amount
	 */
	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount) {
		clientProxy.getClientProduct().changeProduct(index, newPrice, newColor, newSize, amount);
		changeSupport.firePropertyChange("productModified", null, clientProxy.getClientProduct().convertToStringArrayList());
	}

	/**
	 * Finds a Product in database by following id and returns a number of how many Products have been rented.
	 * @param id
	 * @return Number of rented Products to customers.
	 * @throws RemoteException
	 */
	@Override
	public int getRentedAmount(int id) {
		return 0;
	}

	/**
	 * Returns a list of Products filtered by given category.
	 * @param category
	 * @return List containing all products.
	 * @throws RemoteException
	 */
	@Override
	public ProductList getProductsByCategory(EquipmentType category) {
		return clientProxy.getClientProduct().getProductByCategory(category);
	}

	/**
	 * Returns a image from database
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public byte[] getImage(int id) {
		return clientProxy.getClientProduct().getImage(id);
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
