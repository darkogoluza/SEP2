package server.networking;

import server.model.ModelProxy;
import server.model.product.ManageProducts;
import server.model.product.ManageProductsManager;
import shared.networking.ServerProduct;
import shared.objects.product.*;
import shared.util.Utils;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServerProduct implements ServerProduct {
	private ManageProducts productsManager;

	public RMIServerProduct(ManageProducts manageProductsManager) throws RemoteException
	{
		this.productsManager = manageProductsManager;
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size) throws RemoteException{
		productsManager.add(price, color, equipmentType, size);
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
		System.out.println("aa");
		return productsManager.getAllProducts();
	}

	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize)
			throws RemoteException{
		productsManager.changeProduct(index, newPrice, newColor, newSize);
	}

	@Override public void add(Product product)throws RemoteException
	{

	}
}
