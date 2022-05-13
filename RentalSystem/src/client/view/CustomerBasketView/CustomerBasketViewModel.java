package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.basket.ProductsInBasket;
import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.product.Product;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;

public class CustomerBasketViewModel
{
    private ObservableList<ProductsInBasket> productsInBaskets;
    private ManageProducts modelProduct;
    private ManageBasket modelBasket;

    public CustomerBasketViewModel(ModelProxy modelProxy)
    {
        productsInBaskets = FXCollections.observableArrayList();
        modelProduct = modelProxy.getManageProducts();
        modelBasket = modelProxy.getManageBasket();

        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);

    }

    private void modifiedBasket(PropertyChangeEvent propertyChangeEvent) {
        showAllProductsInBasket();
    }


    public ObservableList<ProductsInBasket> getProductsInBaskets() {
        return productsInBaskets;
    }

    public void showAllProductsInBasket()
    {
        productsInBaskets.clear();
        Map<Product, Integer> map = modelBasket.getAllProductsByQuantity();
        for(Map.Entry<Product, Integer> entry : map.entrySet())
        {
            productsInBaskets.add(new ProductsInBasket(entry.getKey(), entry.getValue()));
        }
    }

    public void clearBasket() {
        modelBasket.clear();
    }

    public void removeItemFormBasket(Product product) {
        modelBasket.remove(product);
    }

    public void order()
    {
        modelBasket.order();
    }
}
