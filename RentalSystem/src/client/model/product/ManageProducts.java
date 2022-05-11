package client.model.product;

import shared.objects.*;
import shared.util.PropertyChangeSubject;

public interface ManageProducts extends PropertyChangeSubject {
	void add(double price, Color color, EquipmentType equipmentType, Size size);
	void remove(int index);
	Product getProduct(int index);
	ProductArrayList getAllProducts();
	void changeProduct(int index, double newPrice, Color newColor, Size newSize);
	void showAllProducts();
}
