package client.model.product;

import javafx.collections.ObservableList;
import shared.objects.product.*;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface ManageProducts extends PropertyChangeSubject {
	void add(double price, Color color, EquipmentType equipmentType, Size size);
	void remove(int index);
	Product getProduct(int index);
	ProductList getAllProducts();
	void changeProduct(int index, double newPrice, Color newColor, Size newSize);
	ProductList getProductsByCategory(EquipmentType category);
}
