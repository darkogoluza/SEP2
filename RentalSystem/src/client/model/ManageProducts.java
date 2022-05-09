package client.model;

import shared.objects.Product;
import shared.util.PropertyChangeSubject;

public interface ManageProducts extends PropertyChangeSubject {
	void add(Product product);
	void remove(int id);
}
