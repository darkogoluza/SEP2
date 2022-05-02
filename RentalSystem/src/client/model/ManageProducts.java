package client.model;

import shared.objects.Product;

public interface ManageProducts {
	void add(Product product);
	void remove(int id);
}
