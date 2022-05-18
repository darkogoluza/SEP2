package shared.networking;

import shared.objects.product.*;

import java.rmi.Remote;

public interface ServerProduct extends Remote {
	void add(double price, Color color, EquipmentType equipmentType, Size size);
	void remove(int index);
	Product getProduct(int index);
	ProductList getAllProducts();
	void changeProduct(int index, double newPrice, Color newColor, Size newSize);
	void add(Product product);
}
