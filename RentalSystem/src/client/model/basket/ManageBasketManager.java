package client.model.basket;

import client.networking.ClientProxy;
import server.model.reservation.ManageReservationDatabase;
import shared.networking.model.ManageBasket;
import shared.objects.product.Product;
import shared.objects.product.ProductList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    public void order(Timestamp createAt, Timestamp returnAt) {
        clientProxy.getClientBasket().order(createAt, returnAt);
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
    public ArrayList<String> getAllProductsAsString() {
        Map<Product, Integer> map = getAllProductsByQuantity();
        ProductList allProducts = clientProxy.getClientProduct().getAllProducts();
        ArrayList<String> temp = new ArrayList<>();

        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.getByIndex(i);
            int rented = clientProxy.getClientProduct().getRentedAmount(product.getId());
            int inStock = product.getAmount() - rented;

            for(Map.Entry<Product, Integer> entry : map.entrySet()) {
                if(entry.getKey().equals(product)) {
                    inStock -= entry.getValue();
                }
            }

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
