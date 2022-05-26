package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.ModelProxyManager;
import client.model.basket.ProductsInBasket;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import shared.networking.model.ManageBasket;
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
    private StringProperty totalItemsInBasketProperty;

    private ManageBasket modelBasket;
    private ModelProxy modelProxy;

    public CustomerBasketViewModel(ModelProxy modelProxy)
    {
        productsInBaskets = FXCollections.observableArrayList();
        finalTotalPriceProperty = new SimpleStringProperty();
        userNameProperty = new SimpleStringProperty();
        createDateProperty = new SimpleObjectProperty<>();
        returnDateProperty = new SimpleObjectProperty<>();
        totalItemsInBasketProperty = new SimpleStringProperty();
        this.modelProxy = modelProxy;

        modelBasket = modelProxy.getManageBasket();
        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
        modelBasket.addPropertyChangeListener("finalPriceEvent", this::modifiedBasket);
        totalItemsInBasketProperty.set("" + modelBasket.size());

        finalTotalPriceProperty.set(modelBasket.getTotalPrice() + "");
        userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());

		modelProxy.getManageUser().addPropertyChangeListener("login",
				(event) ->  userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername())
		);
    }

    private void modifiedBasket(PropertyChangeEvent event) {
        showAllProductsInBasket();
        totalItemsInBasketProperty.set("" + event.getNewValue());
        finalTotalPriceProperty.set("" + event.getNewValue());
        userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());
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

    public void logOff() {
        modelBasket.clear();
        modelProxy.getManageUser().logout();
    }

    public StringProperty getTotalItemsInBasketProperty() {
        return totalItemsInBasketProperty;
    }

}
