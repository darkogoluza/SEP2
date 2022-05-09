package client.model;

import shared.objects.Color;
import shared.objects.EquipmentType;
import shared.objects.Product;
import shared.objects.Size;
import shared.util.PropertyChangeSubject;

public interface ManageProducts extends PropertyChangeSubject {
	void add(double price, Color color, EquipmentType equipmentType, Size size);
	void remove(int id);
	void showAllProducts();
}
