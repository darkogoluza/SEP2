package client.model.basket;

import client.model.reservation.ManageReservationDatabase;
import shared.objects.basket.Basket;
import shared.objects.product.Product;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.Map;

public class ManageBasketManager implements ManageBasket {
    private Basket basket;
    private PropertyChangeSupport changeSupport;
    private ManageReservationDatabase reservationDatabase;

    public ManageBasketManager () {
        try {
            this.reservationDatabase = new ManageReservationDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        basket = new Basket("Darko");
        changeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void add(Product product) {
        basket.getProducts().add(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
    }

    @Override
    public Product remove(Product product) {
        Product product1 = basket.getProducts().remove(product.getId());
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        return product1;
    }

    @Override
    public void clear() {
        basket.clear();
        changeSupport.firePropertyChange("modifiedBasket", null, size());
    }

    @Override
    public double getTotalPrice() {
        return basket.getTotalPrice();
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
        Reservation reservation = new Reservation(0, basket.getCustomerUsername(), basket.getProducts());
        try {
            reservationDatabase.save(reservation, getAllProductsByQuantity());
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
