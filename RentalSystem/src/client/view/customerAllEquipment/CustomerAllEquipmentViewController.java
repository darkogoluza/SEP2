package client.view.customerAllEquipment;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import shared.objects.product.EquipmentType;

public class CustomerAllEquipmentViewController
{
    @FXML
    private Label username;
	@FXML
    private Button filterButton;
	@FXML
	private ChoiceBox<String> filterChoiceBox;
    @FXML
    private ListView listOfProducts;
    @FXML
    private Label totalItemsInBasket;

    private ViewHandler viewHandler;
    private CustomerAllEquipmentViewModel viewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf)
    {
        this.viewHandler = viewHandler;
        viewModel = vmf.getCustomerAllEquipmentView();
        username.textProperty().bind(viewModel.getUsernameProperty());
        totalItemsInBasket.textProperty().bind(viewModel.getTotalItemsInBasketProperty());
        listOfProducts.itemsProperty().bind(viewModel.getListOfProductsProperty());
		filterChoiceBox.setItems(viewModel.getCategoriesProperty());
		filterChoiceBox.getSelectionModel().selectFirst();

        viewModel.loadAllProducts();

    }

    public void onLogOff(ActionEvent event)
    {
		viewModel.logOff();
        viewHandler.openLoginView();
    }

    public void onAddToBasket(ActionEvent event)
    {
        if(listOfProducts.getSelectionModel().getSelectedIndex() < 0)
            return;

        viewModel.addProductToBasket(listOfProducts.getSelectionModel().getSelectedIndex());
    }

    public void onGoToBasketButton(ActionEvent event)
    {
        viewHandler.openCustomerBasket();
    }

	public void filter() {
		viewModel.filterByCategory(filterChoiceBox.getSelectionModel().getSelectedIndex());
	}

}

