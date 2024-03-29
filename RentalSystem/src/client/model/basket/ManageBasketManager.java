package client.model.basket;

import client.networking.ClientProxy;
import server.model.reservation.ManageReservationDatabase;
import shared.networking.model.ManageBasket;
import shared.objects.errors.AlertHandler;
import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.user.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class ManageBasketManager implements ManageBasket {

    private ClientProxy clientProxy;
    private PropertyChangeSupport changeSupport;
    private ManageReservationDatabase reservationDatabase;

	/**
	 * Client model for basket managing
	 * @param clientProxy
	 */
    public ManageBasketManager (ClientProxy clientProxy) {
        changeSupport = new PropertyChangeSupport(this);
        this.clientProxy = clientProxy;
    }

	/**
	 * Add product to basket and fire events
	 * @param product
	 */
    @Override
    public void add(Product product) {
        clientProxy.getClientBasket().add(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

	/**
	 * Remove product to basket and fire events
	 * @param product
	 */
    @Override
    public Product remove(Product product) {
        Product product1 = clientProxy.getClientBasket().remove(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
        return product1;
    }

	/**
	 * Clear basket and remove all products from basket
	 */
    @Override
    public void clear() {
        clientProxy.getClientBasket().clear();
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

	/**
	 * Total price of basket
	 * @return
	 */
    @Override
    public String getTotalPrice() {
        return clientProxy.getClientBasket().getTotalPrice();
    }

	/**
	 * Get amount of products in basket
	 * @return
	 */
    @Override
    public int size() {
        return clientProxy.getClientBasket().Size();
    }

	/**
	 * Get all products with quantity as hashmap
	 * @return
	 */
    @Override
    public Map<Product, Integer> getAllProductsByQuantity() {
        return clientProxy.getClientBasket().getAllProductsByQuantity();
    }

	/**
	 * Create order with user object, start time and return time
	 * @param createAt
	 * @param returnAt
	 * @param user
	 */
    @Override
    public void order(Timestamp createAt, Timestamp returnAt, User user) {
        if(!checkIfOrderIsValid()){
            AlertHandler.getInstance().orderNotCreated();
            return;
        }

        clientProxy.getClientBasket().order(createAt, returnAt, user);
        AlertHandler.getInstance().orderCreated();
    }

	/**
	 * Check if basket is empty
	 * @return
	 */
    @Override
    public boolean isEmpty() {
        return clientProxy.getClientBasket().isEmpty();
    }

	/**
	 * Get username of logged user
	 * @return
	 */
    public String getUserName() {
        return clientProxy.getClientUser().getLoggedUser().getUsername();
    }

	/**
	 * Get all products from basket as arraylist for all products
	 * @return
	 */
    @Override
    public ArrayList<String> getAllProductsAsString() {
        ProductList allProducts = clientProxy.getClientProduct().getAllProducts();
        ArrayList<String> temp = new ArrayList<>();
		int inStock;

        for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.getByIndex(i);

			inStock = getAmountOfProductLeftInStock(product);

            String value = String.format("[%s %s] %.02f€    %s",
                    product.getColor(),
                    product.getType().toString(),
                    product.getPrice(),
                    inStock <= 0 ? "out of stock" : inStock + " left in stock"
                    );

            temp.add(value);
        }

        return temp;
    }

	/**
	 * Get all products from basket as arraylist for given product list
	 * @return
	 */
	@Override
	public ArrayList<String> getAllProductsAsString(ProductList allProducts) {
		ArrayList<String> temp = new ArrayList<>();
		int inStock;

		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.getByIndex(i);

			inStock = getAmountOfProductLeftInStock(product);

			String value = String.format("[%s %s] %.02f€    %s",
					product.getColor(),
					product.getType().toString(),
					product.getPrice(),
					inStock <= 0 ? "out of stock" : inStock + " left in stock"
			);

			temp.add(value);
		}

		return temp;
	}

	/**
	 * Get amount of product left in stock
	 * @param product
	 * @return amount of product left in stock
	 */
	public int getAmountOfProductLeftInStock(Product product) {

		Map<Product, Integer> map = getAllProductsByQuantity();

		int rented = clientProxy.getClientProduct().getRentedAmount(product.getId());
		int inStock = product.getAmount() - rented;

		for (Map.Entry<Product, Integer> entry : map.entrySet()) {
			if (entry.getKey().equals(product)) {
				inStock -= entry.getValue();
			}
		}

		// if inStock is -1 return 0
		return Math.max(inStock, 0);
	}

	/**
	 * Check if product is in stock
	 * @param id of product
	 * @return
	 */
    @Override
    public boolean checkIfProductIsInStock(int id) {
        Map<Product, Integer> map = getAllProductsByQuantity();
        ProductList allProducts = clientProxy.getClientProduct().getAllProducts();

        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.getByIndex(i);

            if(product.getId() == id) {
                for (Map.Entry<Product, Integer> entry : map.entrySet()) {
                    if (entry.getKey().equals(product)) {
                        int inStock = product.getAmount() - entry.getValue() - clientProxy.getClientProduct().getRentedAmount(product.getId());
                        if(inStock <= 0)
                            return false;
                    }
                }
            }
        }

        return true;
    }

	/**
	 * Check if all product are in stock
	 * @return
	 */
    public boolean checkIfOrderIsValid() {
        Map<Product, Integer> map = getAllProductsByQuantity();
        ProductList allProducts = clientProxy.getClientProduct().getAllProducts();

        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.getByIndex(i);
                for (Map.Entry<Product, Integer> entry : map.entrySet()) {
                    if (entry.getKey().equals(product)) {
                        int inStock = product.getAmount() - clientProxy.getClientProduct().getRentedAmount(product.getId());
                        if(inStock <= 0)
                            return false;
                    }
                }
        }

        return true;
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
