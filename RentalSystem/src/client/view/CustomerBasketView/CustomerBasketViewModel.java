package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.basket.ProductsInBasket;
import client.model.product.ManageProducts;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import shared.objects.product.Product;
import java.beans.PropertyChangeEvent;
import java.util.Map;

public class CustomerBasketViewModel
{
    private ObservableList<ProductsInBasket> productsInBaskets;
    private StringProperty finalTotalPriceProperty;
    private StringProperty userNameProperty;

    private ManageBasket modelBasket;

    public CustomerBasketViewModel(ModelProxy modelProxy)
    {
        productsInBaskets = FXCollections.observableArrayList();
        finalTotalPriceProperty = new SimpleStringProperty();
        userNameProperty = new SimpleStringProperty();

        modelBasket = modelProxy.getManageBasket();
        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
        modelBasket.addPropertyChangeListener("finalPriceEvent", this::modifiedBasket);

        finalTotalPriceProperty.set(modelBasket.getTotalPrice() + "");
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
        if(modelBasket.isEmpty()){
            showWrongInputDialog();
            return;
        }
        modelBasket.order();
        modelBasket.clear();
    }

    public StringProperty getFinalTotalPriceProperty(){
        return finalTotalPriceProperty;
    }

    public StringProperty getUserNameProperty()
    {
        return userNameProperty;
    }

    private void showWrongInputDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("First you must select witch equipment to reserve");

        alert.showAndWait();
    }
}
