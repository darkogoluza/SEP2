package client.view.customerAllEquipment;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.product.ManageProducts;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import shared.objects.errors.AlertHandler;
import shared.objects.product.Product;

import java.beans.PropertyChangeEvent;

public class CustomerAllEquipmentViewModel
{
    private ListProperty<String> listOfProducts;
    private StringProperty usernameProperty;
    private StringProperty totalItemsInBasketProperty;

    private ManageBasket modelBasket;
    private ManageProducts modelProducts;
	private ModelProxy modelProxy;

	public CustomerAllEquipmentViewModel(ModelProxy modelProxy)
    {
        usernameProperty = new SimpleStringProperty();
        listOfProducts = new SimpleListProperty<>();
        totalItemsInBasketProperty = new SimpleStringProperty();

		this.modelProxy = modelProxy;
        modelBasket = modelProxy.getManageBasket();
        modelProducts = modelProxy.getManageProducts();

        totalItemsInBasketProperty.set("" + modelBasket.size());
        usernameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());
        modelBasket.addPropertyChangeListener("modifiedBasket", this::modifiedBasket);
		modelProxy.getManageUser().addPropertyChangeListener("login",
				(event) ->  {
		            usernameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());
		            loadAllProducts();
		}
		);

    }

	private void login(PropertyChangeEvent propertyChangeEvent) {
		System.out.println(propertyChangeEvent.getNewValue());
	}

	private void modifiedBasket(PropertyChangeEvent event) {
        totalItemsInBasketProperty.set("" + event.getNewValue());
        loadAllProducts();

    }


    public void addProductToBasket(int index)
    {
        Product product = modelProducts.getProduct(index);
        if(!modelBasket.checkIfProductIsInStock(product.getId())) {
            AlertHandler.getInstance().onProductOutOfStock(product);
            return;
        }
        modelBasket.add(product);
    }

    public void loadAllProducts() {
        listOfProducts.set(FXCollections.observableArrayList(modelBasket.getAllProductsAsString()));
    }

    public StringProperty getUsernameProperty()
    {
        return usernameProperty;
    }
    public StringProperty getTotalItemsInBasketProperty() {
        return totalItemsInBasketProperty;
    }
    public ListProperty<String> getListOfProductsProperty() {
        return listOfProducts;
    }

    public void logOff() {
	    modelBasket.clear();
		modelProxy.getManageUser().logout();
    }
}
