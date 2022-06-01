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

	/**
	 * Constructor with initialization
	 * @param modelProxy
	 */
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

	/**
	 * When event was called, this method will be executed
	 * @param event
	 */
    private void modifiedBasket(PropertyChangeEvent event) {
        showAllProductsInBasket();
        totalItemsInBasketProperty.set("" + event.getNewValue());
        finalTotalPriceProperty.set("" + event.getNewValue());
        userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());
    }

	/**
	 * Get products in basket
	 * @return observable arraylist
	 */
    public ObservableList<ProductsInBasket> getProductsInBaskets() {
        return productsInBaskets;
    }

	/**
	 * Display all products
	 */
    public void showAllProductsInBasket()
    {
        productsInBaskets.clear();
        Map<Product, Integer> map = modelBasket.getAllProductsByQuantity();
        for(Map.Entry<Product, Integer> entry : map.entrySet())
        {
            productsInBaskets.add(new ProductsInBasket(entry.getKey(), entry.getValue()));
        }
    }

	/**
	 * Clear the basket
	 */
    public void clearBasket() {
        modelBasket.clear();
    }

	/**
	 * Remove item from basket
	 * @param product
	 */
    public void removeItemFormBasket(Product product) {
        modelBasket.remove(product);
    }

	/**
	 * Place an order
	 */
    public void order()
    {
        if(modelBasket.isEmpty()){
			AlertHandler.getInstance().emptyBasket();
            return;
        }

        Timestamp createAt = Timestamp.valueOf(createDateProperty.getValue().atTime(LocalTime.now()));
        Timestamp returnAt = Timestamp.valueOf(returnDateProperty.getValue().atTime(LocalTime.of(17,0,0)));
        modelBasket.order(createAt, returnAt, modelProxy.getManageUser().getLoggedUser());
        modelBasket.clear();
    }

	/**
	 * get total price string property
	 * @return
	 */
    public StringProperty getFinalTotalPriceProperty(){
        return finalTotalPriceProperty;
    }

	/**
	 * Get username property
	 * @return
	 */
    public StringProperty getUserNameProperty()
    {
        return userNameProperty;
    }

	/**
	 * Get create date property
	 * @return
	 */
    public Property<LocalDate> getCreateDateProperty() {
        return createDateProperty;
    }

	/**
	 * get return date property
	 * @return
	 */
    public Property<LocalDate> getReturnDateProperty() {
        return returnDateProperty;
    }

	/**
	 * Log off user
	 */
    public void logOff() {
        modelBasket.clear();
        modelProxy.getManageUser().logout();
    }

	/**
	 * get all items in basket property
	 * @return
	 */
    public StringProperty getTotalItemsInBasketProperty() {
        return totalItemsInBasketProperty;
    }

}
