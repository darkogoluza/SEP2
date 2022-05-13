package client.model.basket;

import shared.objects.basket.Basket;
import shared.objects.product.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageBasketManager implements ManageBasket {
    private Basket basket;
    private PropertyChangeSupport changeSupport;

    public ManageBasketManager () {
        basket = new Basket("Darko");
        changeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void add(Product product) {
        basket.getProducts().add(product);
        changeSupport.firePropertyChange("addedProduct", null, basket.getProducts().size());
    }

    @Override
    public Product remove(Product product) {
        return basket.getProducts().remove(product.getId());
    }

    @Override
    public void clear() {
        basket.clear();;
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
