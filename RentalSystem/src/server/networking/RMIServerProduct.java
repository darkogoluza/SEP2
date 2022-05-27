package server.networking;

import javafx.scene.image.Image;
import shared.networking.model.ManageProducts;
import shared.networking.server.ServerProduct;
import shared.objects.product.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerProduct implements ServerProduct {
	private ManageProducts productsManager;

	public RMIServerProduct(ManageProducts manageProductsManager) throws RemoteException
	{
		this.productsManager = manageProductsManager;
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size, String file) throws RemoteException{
		productsManager.add(price, color, equipmentType, size, file);
	}

	@Override
	public void remove(int index)throws RemoteException {
		productsManager.remove(index);
	}

	@Override
	public Product getProduct(int index) throws RemoteException{
		return productsManager.getProduct(index);
	}

	@Override
	public ProductList getAllProducts() throws RemoteException {
		return productsManager.getAllProducts();
	}

	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize)
			throws RemoteException{
		productsManager.changeProduct(index, newPrice, newColor, newSize);
	}

	@Override
	public void add(Product product) throws RemoteException {

	}

	@Override
	public byte[] getImage(int id) throws RemoteException {
		return productsManager.getImage(id);
	}

	@Override
	public ProductList getProductsByCategory(EquipmentType category) throws RemoteException {
		return productsManager.getProductsByCategory(category);
	}
}
