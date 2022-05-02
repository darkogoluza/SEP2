package administrator.model;

import shared.objects.Product;

import java.util.ArrayList;

public class ManageProductsManager implements ManageProducts {

	private ArrayList<Product> list;

	public ManageProductsManager() {
		list = new ArrayList<>();
	}

	@Override
	public void add(Product product) {
		list.add(product);
	}

	@Override
	public void remove(int id) {
		for (Product product :
				list) {
			// TODO: 02/05/2022 product id is String or int?
			if (product.getId() == id) {
				list.remove(product);
			}
		}

	}
}
