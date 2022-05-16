package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.basket.ProductsInBasket;
import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.product.Product;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;

public class CustomerBasketViewModel
{
    private ObservableList<ProductsInBasket> productsInBaskets;
    private StringProperty finalTotalPriceProperty;
    private StringProperty userNameProperty;

    private ManageProducts modelProduct;
    private ManageBasket modelBasket;

    public CustomerBasketViewModel(ModelProxy modelProxy)
    {
        productsInBaskets = FXCollections.observableArrayList();
        finalTotalPriceProperty = new SimpleStringProperty();
        userNameProperty = new SimpleStringProperty();

        modelProduct = modelProxy.getManageProducts();
        modelBasket = modelProxy.getManageBasket();

        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
        finalTotalPriceProperty.set(modelBasket.getTotalPrice() + "");

        modelBasket.addPropertyChangeListener("finalPriceEvent", this::modifiedBasket);
    }

    private void modifiedBasket(PropertyChangeEvent event) {
        showAllProductsInBasket();
        finalTotalPriceProperty.set("" + event.getNewValue());
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

    public StringProperty getFinalTotalPriceProperty(){
        return finalTotalPriceProperty;
    }
    public StringProperty getUserNameProperty()
    {
        return userNameProperty;
    }
}
