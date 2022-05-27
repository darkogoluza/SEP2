package shared.networking.model;

import shared.objects.product.*;
import shared.util.PropertyChangeSubject;

public interface ManageProducts extends PropertyChangeSubject {
	void add(double price, Color color, EquipmentType equipmentType, Size size, String file);
	void remove(int index);
	Product getProduct(int index);
	ProductList getAllProducts();
	void changeProduct(int index, double newPrice, Color newColor, Size newSize);
    ProductList getProductsByCategory(EquipmentType category);
	byte[] getImage(int id);
}
