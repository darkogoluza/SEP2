package client.view.customerAllEquipment;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.product.ManageProducts;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import shared.objects.product.Product;

import java.beans.PropertyChangeEvent;

public class CustomerAllEquipmentViewModel
{
    private ListProperty<String> listOfProducts;
    private StringProperty editableLabelUserNameProperty;
    private StringProperty totalItemsInBasketProperty;

    private ManageBasket modelBasket;
    private ManageProducts modelProducts;

    public CustomerAllEquipmentViewModel(ModelProxy modelProxy)
    {
        editableLabelUserNameProperty = new SimpleStringProperty();
        listOfProducts = new SimpleListProperty<>();
        totalItemsInBasketProperty = new SimpleStringProperty();

        modelBasket = modelProxy.getManageBasket();
        modelProducts = modelProxy.getManageProducts();

        totalItemsInBasketProperty.set("Total Items in basket: " + modelBasket.size());

        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
    }

    private void modifiedBasket(PropertyChangeEvent event) {
        totalItemsInBasketProperty.set("Total Items in basket: " + event.getNewValue());

    }


    public void addProductToBasket(int index)
    {
        Product product = modelProducts.getProduct(index);
        modelBasket.add(product);
    }

    public void loadAllProducts() {
        listOfProducts.set(FXCollections.observableArrayList(modelProducts.getAllProducts().convertToStringArrayList()));
    }

    public void updateUserName(String userName)
    {
        editableLabelUserNameProperty.setValue(userName);
    }

    public StringProperty getEditableLabelUserNameProperty()
    {
        return editableLabelUserNameProperty;
    }
    public StringProperty getTotalItemsInBasketProperty() {
        return totalItemsInBasketProperty;
    }
    public ListProperty<String> getListOfProductsProperty() {
        return listOfProducts;
    }

}
