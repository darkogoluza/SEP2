package server.networking;

import shared.networking.ServerProduct;
import shared.objects.product.*;
import shared.util.Utils;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServerProduct implements ServerProduct {

	public RMIServerProduct() {
		try {
			UnicastRemoteObject.exportObject(this, Utils.SERVER_PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size) {

	}

	@Override
	public void remove(int index) {

	}

	@Override
	public Product getProduct(int index) {
		return null;
	}

	@Override
	public ProductList getAllProducts() {
		return null;
	}

	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize) {

	}
}
