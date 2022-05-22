package server.model.basket;

import server.model.reservation.ManageReservationDatabase;
import server.model.user.ManageUserManager;
import shared.objects.basket.Basket;
import shared.objects.product.Product;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.Map;

public class ManageBasketManager implements ManageBasket
{
    private Basket basket;
    private PropertyChangeSupport changeSupport;
    private ManageReservationDatabase reservationDatabase;

    public ManageBasketManager () {
        try {
            this.reservationDatabase = new ManageReservationDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        basket = new Basket();
        changeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void add(Product product) {
        basket.getProducts().add(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

    @Override
    public Product remove(Product product) {
        Product product1 = basket.getProducts().remove(product.getId());
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
        return product1;
    }

    @Override
    public void clear() {
        basket.clear();
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

    @Override
    public String getTotalPrice() {
        return String.format("%.02f€", basket.getTotalPrice());

    }

    @Override
    public int size() {
        return basket.getProducts().size();
    }

    @Override
    public Map<Product, Integer> getAllProductsByQuantity() {
        return basket.getAllProductsByQuantity();
    }

    @Override
    public void order() {
        int reservationId = 0;
        try {
            reservationId = reservationDatabase.getUniqueId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		basket.setCustomerUsername(ManageUserManager.user.getUsername());
        Reservation reservation = new Reservation(reservationId, basket.getCustomerUsername(), basket.getProducts());
        try {
            reservationDatabase.save(reservation, getAllProductsByQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isEmpty() {
        return basket.getProducts().isEmpty();
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
