package shared.networking.model;

import shared.objects.product.*;
import shared.util.PropertyChangeSubject;

public interface ManageProducts extends PropertyChangeSubject {
	void add(double price, Color color, EquipmentType equipmentType, Size size, int amount);
	void remove(int index);
	Product getProduct(int index);
	ProductList getAllProducts();
	void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount);
	void showAllProducts();
	int getRentedAmount(int id);
    ProductList getProductsByCategory(EquipmentType category);
}
