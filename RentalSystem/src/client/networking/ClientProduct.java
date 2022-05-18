package client.networking;

import shared.networking.Server;
import shared.networking.ServerProduct;
import shared.objects.product.*;
import shared.util.Utils;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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

	public void add(double price, Color color, EquipmentType equipmentType, Size size) {
		try
		{
			server.add(price, color, equipmentType, size);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

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

	public void changeProduct(int index, double newPrice, Color newColor, Size newSize) {
		try
		{
			server.changeProduct(index, newPrice, newColor, newSize);
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
}
