package client.view.customerAllEquipment;

import client.model.ModelProxy;
import client.model.product.ManageProducts;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import shared.objects.product.Product;

public class CustomerAllEquipmentViewModel
{
    private ListProperty<String> listOfProducts;
    private StringProperty editableLabelUserNameProperty;
    private ModelProxy modelProxy;

    public CustomerAllEquipmentViewModel(ModelProxy modelProxy)
    {
        editableLabelUserNameProperty = new SimpleStringProperty();
        listOfProducts = new SimpleListProperty<>();
        this.modelProxy = modelProxy;
    }

    public void addProductToBasket(int index)
    {
		System.out.println(listOfProducts.get(index));
//		modelProxy.getManageReservations().
        //TODO there will be another model to handle this.
        //model.add(product);
    }

    public void loadAllProducts() {
        listOfProducts.set(FXCollections.observableArrayList(modelProxy.getManageProducts().getAllProducts().convertToStringArrayList()));
    }

    public void updateUserName(String userName)
    {
        editableLabelUserNameProperty.setValue(userName);
    }

    public StringProperty getEditableLabelUserNameProperty()
    {
        return editableLabelUserNameProperty;
    }
    public ListProperty<String> getListOfProductsProperty() {
        return listOfProducts;
    }
}
