package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.product.Product;

public class CustomerBasketViewModel
{
    private ObservableList<Product> products;
    private ManageProducts model;

    public CustomerBasketViewModel(ModelProxy modelProxy)
    {
        products = FXCollections.observableArrayList();
        model = modelProxy.getManageProducts();

    }



}
