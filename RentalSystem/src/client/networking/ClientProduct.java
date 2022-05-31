package client.networking;

import javafx.scene.image.Image;
import shared.networking.server.Server;
import shared.networking.server.ServerProduct;
import shared.objects.product.*;
import shared.util.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 * Class coumnicates with RMIServerProduct and deals with saving and loading of Products.
 */
public class ClientProduct implements Remote, Serializable
{

	private ServerProduct server;

	public ClientProduct()
	{
		try
		{
//			UnicastRemoteObject.exportObject(this, 0);
			Registry registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
			Server serverProxy = (Server) registry.lookup(Utils.SERVER_RENTAL);

			server = serverProxy.getProductServer();
		}
		catch (RemoteException | NotBoundException e)
		{
			e.printStackTrace();
		}


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
	public void add(double price, Color color, EquipmentType equipmentType, Size size, int amount, String file) {
		try
		{
			server.add(price, color, equipmentType, size, amount, file);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Remove a single Product from database by index.
	 * @param index
	 * @throws RemoteException
	 */
	public void remove(int index) {
		try
		{
			server.remove(index);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns a single Product from database by index.
	 * @param index
	 * @return Object containing all information about Product.
	 * @throws RemoteException
	 */
	public Product getProduct(int index) {
		try
		{
			return server.getProduct(index);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all products from database.
	 * @return List containing all the products.
	 * @throws RemoteException
	 */
	public ProductList getAllProducts() {
		try
		{
			return server.getAllProducts();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return null;
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
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount) {
		try
		{
			server.changeProduct(index, newPrice, newColor, newSize, amount);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}


	public void add(Product product)
	{
		try
		{
			server.add(product);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of Products converted to String
	 * @return
	 * @throws RemoteException
	 */
    public ArrayList<String> convertToStringArrayList() {
		try {
			return server.getAllProducts().convertToStringArrayList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns a list of Products filtered by given category.
	 * @param category
	 * @return List containing all products.
	 * @throws RemoteException
	 */
    public ProductList getProductByCategory(EquipmentType category) {
		try {
			return server.getProductsByCategory(category);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Finds a Product in database and returns a number of how many Products have been rented.
	 * @param id
	 * @return Number of rented Products to customers.
	 * @throws RemoteException
	 */
	public int getRentedAmount(int id) {
		try {
			return server.getRentedAmount(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Returns a image with given id.
	 * @param id
	 * @return List containing all products.
	 * @throws RemoteException
	 */
	public byte[] getImage(int id) {
		try {
			return server.getImage(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
