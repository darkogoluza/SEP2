package client.model.basket;

import client.networking.ClientProxy;
import server.model.reservation.ManageReservationDatabase;
import shared.objects.product.Product;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

public class ManageBasketManager implements ManageBasket {

    private ClientProxy clientProxy;
    private PropertyChangeSupport changeSupport;
    private ManageReservationDatabase reservationDatabase;

    public ManageBasketManager (ClientProxy clientProxy) {
        changeSupport = new PropertyChangeSupport(this);
        this.clientProxy = clientProxy;
    }

    @Override
    public void add(Product product) {
        clientProxy.getClientBasket().add(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

    @Override
    public Product remove(Product product) {
        Product product1 = clientProxy.getClientBasket().remove(product);
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
        return product1;
    }

    @Override
    public void clear() {
        clientProxy.getClientBasket().clear();
        changeSupport.firePropertyChange("modifiedBasket", null, size());
        changeSupport.firePropertyChange("finalPriceEvent", null, getTotalPrice());
    }

    @Override
    public String getTotalPrice() {
        return clientProxy.getClientBasket().getTotalPrice();
    }

    @Override
    public int size() {
        return clientProxy.getClientBasket().Size();
    }

    @Override
    public Map<Product, Integer> getAllProductsByQuantity() {
        return clientProxy.getClientBasket().getAllProductsByQuantity();
    }

    @Override
    public void order() {
        clientProxy.getClientBasket().order();
    }

    @Override
    public boolean isEmpty() {
        return clientProxy.getClientBasket().isEmpty();
    }

    @Override
    public String getUserName() {
        return clientProxy.getClientUser().getLoggedUser().getUsername();
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
