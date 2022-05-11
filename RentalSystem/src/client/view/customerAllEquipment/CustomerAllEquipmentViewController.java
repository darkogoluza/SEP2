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

    private ViewHandler viewHandler;
    private CustomerAllEquipmentViewModel customerAllEquipmentViewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf)
    {
        this.viewHandler = viewHandler;
        customerAllEquipmentViewModel = vmf.getCustomerAllEquipmentView();
        editableLabelUserName.textProperty().bind(customerAllEquipmentViewModel.getEditableLabelUserNameProperty());
    }

    public void onLogOff(ActionEvent event)
    {
        viewHandler.openAdministratorView();
    }

    public void onAddToBasket(ActionEvent event)
    {
        listOfProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        customerAllEquipmentViewModel.addProductToBasket((Product) listOfProducts.getSelectionModel().getSelectedItem());
    }

    public void onGoToBasketButton(ActionEvent event)
    {
        //open new view
    }

    public void updateUserName(ActionEvent event, String userName)
    {
        customerAllEquipmentViewModel.updateUserName(userName);
    }
}

