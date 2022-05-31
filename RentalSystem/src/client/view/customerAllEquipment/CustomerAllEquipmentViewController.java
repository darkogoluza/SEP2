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

/**
 * ViewController for CustomerAllEquipmentView
 */
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

  /**
   * constructor
   * @param viewHandler
   * @param vmf
   */
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

  /**
   * log out user on event and opens LoginView
   * @param event
   */
  public void onLogOff(ActionEvent event)
    {
		viewModel.logOff();
        viewHandler.openLoginView();
    }

  /**
   * add selected item to basket
   * @param event
   */
  public void onAddToBasket(ActionEvent event)
    {
        if(listOfProducts.getSelectionModel().getSelectedIndex() < 0)
            return;

        viewModel.addProductToBasket(listOfProducts.getSelectionModel().getSelectedIndex());
    }

  /**
   * Open CustomerBasket view after clicking onGoToBasketButton
   * @param event
   */
  public void onGoToBasketButton(ActionEvent event)
    {
        viewHandler.openCustomerBasket();
    }

  /**
   * Open CustomerAllEquipmentView after clicking backButton
   * @param event
   */
    public void backButton(ActionEvent event)
    {
        viewHandler.openCustomerAllEquipmentView();
    }

  /**
   * Open CustomerAllOrdersView on event
   * @param event
   */
    public void onGoToReservations(ActionEvent event)
    {
        viewHandler.openCustomerAllOrdersView();
    }

  /**
   * method filter products by category
   */
	public void filter() {
		viewModel.filterByCategory(filterChoiceBox.getSelectionModel().getSelectedIndex());
	}

  /**
   * method open ProductDetailsView
   */
	public void openProduct() {
		viewHandler.openProductDetailsView(viewModel.getIdOfProductWithIndex(listOfProducts.getSelectionModel().getSelectedIndex()));
	}

	public void onFilterChoiceChanged(ActionEvent event) {
		filter();
	}
}

