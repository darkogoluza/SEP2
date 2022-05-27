package server.networking;

import javafx.scene.image.Image;
import shared.networking.model.ManageProducts;
import shared.networking.server.ServerProduct;
import shared.objects.product.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class manly deals with saving and loading of Products.
 */
public class RMIServerProduct implements ServerProduct {
	private ManageProducts productsManager;

	/**
	 * @param manageProductsManager Contains all the functionality.
	 * @throws RemoteException Object was not exported correctly.
	 */
	public RMIServerProduct(ManageProducts manageProductsManager) throws RemoteException
	{
		this.productsManager = manageProductsManager;
		UnicastRemoteObject.exportObject(this, 0);
	}

	/**
	 * Add a single Product to database.
	 * @param price
	 * @param color
	 * @param equipmentType
	 * @param size
	 * @param amount
	 * @throws RemoteException
	 */
	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size, int amount, String file) throws RemoteException{
		productsManager.add(price, color, equipmentType, size, amount, file);
	}

	/**
	 * Remove a single Product from database by index.
	 * @param index
	 * @throws RemoteException
	 */
	@Override
	public void remove(int index)throws RemoteException {
		productsManager.remove(index);
	}

	/**
	 * Returns a single Product from database by index.
	 * @param index
	 * @return Object containing all information about Product.
	 * @throws RemoteException
	 */
	@Override
	public Product getProduct(int index) throws RemoteException{
		return productsManager.getProduct(index);
	}

	/**
	 * Returns all products from database.
	 * @return List containing all the products.
	 * @throws RemoteException
	 */
	@Override
	public ProductList getAllProducts() throws RemoteException {
		return productsManager.getAllProducts();
	}

	/**
	 * Finds Product in database by index and changes all the information on it.
	 * @param index
	 * @param newPrice
	 * @param newColor
	 * @param newSize
	 * @param amount
	 * @throws RemoteException
	 */
	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount)
			throws RemoteException{
		productsManager.changeProduct(index, newPrice, newColor, newSize, amount);
	}

	@Override public void add(Product product)throws RemoteException
	{

	}

	/**
	 * Finds a Product in database and returns a number of how many Products have been rented.
	 * @param id
	 * @return Number of rented Products to customers.
	 * @throws RemoteException
	 */
	@Override
	public int getRentedAmount(int id) throws RemoteException {
		return productsManager.getRentedAmount(id);
	}

	/**
	 * Returns a list of Products filtered by given category.
	 * @param category
	 * @return List containing all products.
	 * @throws RemoteException
	 */
	@Override
	public ProductList getProductsByCategory(EquipmentType category) throws RemoteException {
		return productsManager.getProductsByCategory(category);
	}

	@Override
	public byte[] getImage(int id) throws RemoteException {
		return productsManager.getImage(id);
	}
}
