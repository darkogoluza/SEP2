package client.view.customerAllEquipment;

import client.model.ModelProxy;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import shared.objects.errors.AlertHandler;
import javafx.collections.ObservableList;
import shared.networking.model.ManageBasket;
import shared.networking.model.ManageProducts;
import shared.objects.product.EquipmentType;
import shared.objects.product.Product;

import java.beans.PropertyChangeEvent;

public class CustomerAllEquipmentViewModel
{
    private ListProperty<String> listOfProducts;
    private StringProperty usernameProperty;
    private StringProperty totalItemsInBasketProperty;
	private ObservableList<String> categoriesProperty;

    private ManageBasket modelBasket;
    private ManageProducts modelProducts;
	private ModelProxy modelProxy;

	public CustomerAllEquipmentViewModel(ModelProxy modelProxy)
    {
        usernameProperty = new SimpleStringProperty();
        listOfProducts = new SimpleListProperty<>();
        totalItemsInBasketProperty = new SimpleStringProperty();
		categoriesProperty = FXCollections.observableArrayList(
				getEquipmentTypes()
		);

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

	private void modifiedBasket(PropertyChangeEvent event) {
        totalItemsInBasketProperty.set("Total Items in basket: " + event.getNewValue());
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

	public ObservableList<String> getCategoriesProperty() {
		return categoriesProperty;
	}

    public void logOff() {
	    modelBasket.clear();
		modelProxy.getManageUser().logout();
    }

	public void filterByCategory(int index) {
		if (index == 0) {
			loadAllProducts();
		}
		else {
			EquipmentType category = EquipmentType.valueOf( categoriesProperty.get(index) );
			listOfProducts.set(FXCollections.observableArrayList(modelProducts.getProductsByCategory(category).convertToStringArrayList()));
		}
	}

	private ObservableList<String> getEquipmentTypes() {
		ObservableList<String> types = FXCollections.observableArrayList();
		types.add("show all");

		for (EquipmentType type :
				EquipmentType.values()) {
			types.add(type.toString());
		}

		return types;
	}
}
