package server.networking;

import server.model.product.ManageProducts;
import shared.networking.ServerProduct;
import shared.objects.product.*;
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
	public void add(double price, Color color, EquipmentType equipmentType, Size size, int amount) throws RemoteException{
		productsManager.add(price, color, equipmentType, size, amount);
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
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount)
			throws RemoteException{
		productsManager.changeProduct(index, newPrice, newColor, newSize, amount);
	}

	@Override public void add(Product product)throws RemoteException
	{

	}
}
