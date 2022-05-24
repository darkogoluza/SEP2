package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.basket.ProductsInBasket;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import shared.objects.errors.AlertHandler;
import shared.objects.product.Product;
import java.beans.PropertyChangeEvent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class CustomerBasketViewModel
{
    private ObservableList<ProductsInBasket> productsInBaskets;
    private StringProperty finalTotalPriceProperty;
    private StringProperty userNameProperty;
    private Property<LocalDate> createDateProperty;
    private Property<LocalDate> returnDateProperty;
    private ManageBasket modelBasket;

    public CustomerBasketViewModel(ModelProxy modelProxy)
    {
        productsInBaskets = FXCollections.observableArrayList();
        finalTotalPriceProperty = new SimpleStringProperty();
        userNameProperty = new SimpleStringProperty();
        createDateProperty = new SimpleObjectProperty<>();
        returnDateProperty = new SimpleObjectProperty<>();

        modelBasket = modelProxy.getManageBasket();
        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
        modelBasket.addPropertyChangeListener("finalPriceEvent", this::modifiedBasket);

        finalTotalPriceProperty.set(modelBasket.getTotalPrice() + "");
        userNameProperty.set(modelBasket.getUserName());
    }

    private void modifiedBasket(PropertyChangeEvent event) {
        showAllProductsInBasket();
        finalTotalPriceProperty.set("" + event.getNewValue());
        userNameProperty.set(modelBasket.getUserName());
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
			AlertHandler.getInstance().emptyBasket();
            return;
        }

        Timestamp createAt = Timestamp.valueOf(createDateProperty.getValue().atTime(LocalTime.now()));
        Timestamp returnAt = Timestamp.valueOf(returnDateProperty.getValue().atTime(LocalTime.of(17,0,0)));
        modelBasket.order(createAt, returnAt);
        AlertHandler.getInstance().orderCreated();
        modelBasket.clear();
    }

    public StringProperty getFinalTotalPriceProperty(){
        return finalTotalPriceProperty;
    }

    public StringProperty getUserNameProperty()
    {
        return userNameProperty;
    }

    public Property<LocalDate> getCreateDateProperty() {
        return createDateProperty;
    }

    public Property<LocalDate> getReturnDateProperty() {
        return returnDateProperty;
    }

}
