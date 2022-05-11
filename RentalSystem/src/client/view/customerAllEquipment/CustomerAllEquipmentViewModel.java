package client.view.customerAllEquipment;

import client.model.product.ManageProducts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.product.Product;

public class CustomerAllEquipmentViewModel
{
    private ObservableList<String> observableListOfProducts;
    private StringProperty editableLabelUserNameProperty;
    private ManageProducts model;

    public CustomerAllEquipmentViewModel(ManageProducts model)
    {
        editableLabelUserNameProperty = new SimpleStringProperty();
        observableListOfProducts = FXCollections.observableArrayList();
        this.model = model;
    }
    public void addProductToBasket(Product product)
    {
        //TODO there will be another model to handle this.
        //model.add(product);
    }

    public void updateUserName(String userName)
    {
        editableLabelUserNameProperty.setValue(userName);
    }

    public StringProperty getEditableLabelUserNameProperty()
    {
        return editableLabelUserNameProperty;
    }
}
