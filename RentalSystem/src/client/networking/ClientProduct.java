package client.networking;

import shared.networking.ServerProduct;
import shared.objects.product.*;
import shared.util.Utils;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientProduct implements Remote {

	private ServerProduct server;

	public ClientProduct() {
		try {
			UnicastRemoteObject.exportObject(this, 0);
			Registry registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
			server = (ServerProduct) registry.lookup(Utils.SERVER_PRODUCT);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public void add(double price, Color color, EquipmentType equipmentType, Size size) {
		server.add(price, color, equipmentType, size);
	}

	public void remove(int index) {
		server.remove(index);
	}

	public Product getProduct(int index) {
		return server.getProduct(index);
	}

	public ProductList getAllProducts() {
		return server.getAllProducts();
	}

	public void changeProduct(int index, double newPrice, Color newColor, Size newSize) {
		server.changeProduct(index, newPrice, newColor, newSize);
	}
}
