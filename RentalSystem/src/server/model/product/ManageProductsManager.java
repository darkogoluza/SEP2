package server.model.product;

import shared.networking.model.ManageProducts;
import shared.objects.product.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

/**
 * Class manly deals with loading and saving of Products.
 */
public class ManageProductsManager implements ManageProducts
{
	private ProductList list;
	private PropertyChangeSupport changeSupport;
	private ManageProductDatabase manageProductDatabase;

	/**
	 * Instantiates a database manager.
	 */
	public ManageProductsManager() {
		list = new ProductList();
		changeSupport = new PropertyChangeSupport(this);
		try {
			manageProductDatabase = new ManageProductDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add product to list
	 * @param price
	 * @param color
	 * @param equipmentType
	 * @param size
	 */
	@Override
	public void add(double price, Color color, EquipmentType equipmentType, Size size, int amount) {
		try {
			Product product = list.add(price, color, equipmentType, size, amount);
			changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
			manageProductDatabase.save(product);
			update();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Remove selected item with index from list
	 * @param index of product
	 */
	@Override
	public void remove(int index) {
		try {
			Product product = list.removeByIndex(index);
			changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
			manageProductDatabase.remove(product);
			update();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get product from list @todo change this for database when implemented
	 * @param index of product
	 * @return Product
	 */
	@Override
	public Product getProduct(int index) {
		update();
		return list.getByIndex(index);
	}

	@Override
	public ProductList getAllProducts() {
		update();
		return list;
	}

	/**
	 * Update price, color and size of selected product using index of product
	 * @param index
	 * @param newPrice
	 * @param newColor
	 * @param newSize
	 */
	@Override
	public void changeProduct(int index, double newPrice, Color newColor, Size newSize, int amount) {
		try {
			Product product = list.getByIndex(index);
			product.setPrice(newPrice);
			product.setColor(newColor);
			product.setSize(newSize);
			product.setAmount(amount);
			manageProductDatabase.change(product);
			list.change(index, newPrice, newColor, newSize);

			update();
			changeSupport.firePropertyChange("productModified", null, list.convertToStringArrayList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showAllProducts() {

	}

	/**
	 * Returns list of Products filtered by EquipmentType
	 * @param category
	 * @return
	 */
	@Override
	public ProductList getProductsByCategory(EquipmentType category) {
		return list.getAllByCategory(category);
	}

	/**
	 * Returns a rented quantity of a single Product that matches a given ID.
	 * @param id
	 * @return
	 */
	@Override
	public int getRentedAmount(int id) {
		try {
			return manageProductDatabase.getRentedAmount(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Updates internal list of products by loading data from database.
	 */
	private void update() {
		try {
			list = manageProductDatabase.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(name, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(name, listener);
	}
}
