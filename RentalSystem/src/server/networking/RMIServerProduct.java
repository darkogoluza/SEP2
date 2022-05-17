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

	public RMIServerProduct(ModelProxy modelProxy) {
		this.productsManager = modelProxy.getManageProducts();

		try {
			UnicastRemoteObject.exportObject(this, Utils.SERVER_PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size) {
		productsManager.add(price, color, equipmentType, size);
	}

	@Override
	public void remove(int index) {
		productsManager.remove(index);
	}

	@Override
	public Product getProduct(int index) {
		return productsManager.getProduct(index);
	}

	@Override
	public ProductList getAllProducts() {
		return productsManager.getAllProducts();
	}

	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize) {
		productsManager.changeProduct(index, newPrice, newColor, newSize);
	}
}
