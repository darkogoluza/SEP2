package shared.networking.server;

import shared.objects.product.*;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerProduct extends Remote {
	void add(double price, Color color, EquipmentType equipmentType, Size size, int amount, String file)throws RemoteException;
	void remove(int index)throws RemoteException;
	Product getProduct(int index)throws RemoteException;
	ProductList getAllProducts()throws RemoteException;
	void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount)throws RemoteException;
	void add(Product product)throws RemoteException;
	byte[] getImage(int id) throws RemoteException;
	int getRentedAmount(int id) throws RemoteException;
    ProductList getProductsByCategory(EquipmentType category) throws RemoteException;
}
