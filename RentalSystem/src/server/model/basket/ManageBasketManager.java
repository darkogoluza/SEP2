package server.model.basket;

import server.model.reservation.ManageReservationDatabase;
import server.model.user.ManageUserManager;
import shared.networking.model.ManageBasket;
import shared.objects.basket.Basket;
import shared.objects.product.Product;
import shared.objects.reservation.Reservation;
import shared.objects.user.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class manly deals with Adding and removing Products from basket.
 */
public class ManageBasketManager implements ManageBasket
{
    private Basket basket;
    private PropertyChangeSupport changeSupport;
    private ManageReservationDatabase reservationDatabase;

    /**
     * Initializes a database manager.
     */
    public ManageBasketManager () {
        try {
            this.reservationDatabase = new ManageReservationDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        basket = new Basket();
        changeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Adds a single Product to Basket.
     * @param product
     */
    @Override
    public void add(Product product) {
        basket.getProducts().add(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

    /**
     * Removes a single Product from basket.
     * @param product
     * @return
     */
    @Override
    public Product remove(Product product) {
        Product product1 = basket.getProducts().remove(product.getId());
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
        return product1;
    }

    /**
     * Empties the Basket from all Products.
     */
    @Override
    public void clear() {
        basket.clear();
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

    /**
     * Returns formatted sum of Product prices.
     * @return
     */
    @Override
    public String getTotalPrice() {
        return String.format("%.02f€", basket.getTotalPrice());

    }

    /**
     * Returns count of all Products in Basket.
     * @return
     */
    @Override
    public int size() {
        return basket.getProducts().size();
    }

    /**
     * Returns a Map that contains Product as a key and quantity as Value.
     * Value for each key just tells how many of that Product the Basket contains.
     * @return
     */
    @Override
    public Map<Product, Integer> getAllProductsByQuantity() {
        return basket.getAllProductsByQuantity();
    }

    /**
     * Creates new Order with createAt and returnAt Timestamps passed from view.
     * @param createAt
     * @param returnAt
     */
    @Override
    public void order(Timestamp createAt, Timestamp returnAt, User user) {
        int reservationId = 0;
        try {
            reservationId = reservationDatabase.getUniqueId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		basket.setCustomerUsername(ManageUserManager.user.getUsername());
        Reservation reservation = new Reservation(reservationId, user.getUsername(), basket.getProducts());
        reservation.setCreateAt(createAt);
        reservation.setExpiresAt(returnAt);
        try {
            reservationDatabase.save(reservation, getAllProductsByQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the Basket is empty.
     * @return
     */
    @Override
    public boolean isEmpty() {
        return basket.getProducts().isEmpty();
    }

    /**
     * Checks if the Product with matching ID is in stock, and can be ordered.
     * @param id
     * @return
     */
	@Override
	public boolean checkIfProductIsInStock(int id) {
		return false;
	}

    /**
     * Returns all the Products as a ArrayList of strings.
     * Every Product is converted in to a string plus quantity.
     * @return
     */
	@Override
	public ArrayList<String> getAllProductsAsString() {
		return null;
	}

	/**
	 * Get amount of product left in stock
	 * @param product of product
	 * @return amount of product left in stock
	 */
	@Override
	public int getAmountOfProductLeftInStock(Product product) {
//		Product product = basket.getProducts().get(id);
//
//		int rented = clientProxy.getClientProduct().getRentedAmount(product.getId());
//		int inStock = product.getAmount() - rented;
//
//		System.out.println(rented + " " + inStock);

		return 0;
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
