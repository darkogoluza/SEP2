package client.view.customerAllEquipment;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import shared.objects.product.Product;

public class CustomerAllEquipmentViewController
{
    @FXML
    private Label editableLabelUserName;
    @FXML
    private Button logOffButton;
    @FXML
    private Button addToBasketButton;
    @FXML
    private Button gotToBasketButton;
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
        editableLabelUserName.textProperty().bind(viewModel.getEditableLabelUserNameProperty());
        totalItemsInBasket.textProperty().bind(viewModel.getTotalItemsInBasketProperty());
        listOfProducts.itemsProperty().bind(viewModel.getListOfProductsProperty());
        viewModel.loadAllProducts();
    }

    public void onLogOff(ActionEvent event)
    {
        viewHandler.openAdministratorView();
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

    public void updateUserName(ActionEvent event, String userName)
    {
        viewModel.updateUserName(userName);
    }
}

