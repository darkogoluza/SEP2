package client.view.CustomerBasketView;

import client.model.ModelProxy;
import client.model.basket.ProductsInBasket;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.networking.model.ManageBasket;
import shared.objects.errors.AlertHandler;
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
        userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());

		modelProxy.getManageUser().addPropertyChangeListener("login",
				(event) ->  userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername())
		);
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
			AlertHandler.getInstance().emptyBasket();
            return;
        }
        modelBasket.order();

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

}
