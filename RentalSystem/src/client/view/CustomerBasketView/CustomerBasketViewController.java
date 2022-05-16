package client.view.CustomerBasketView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.basket.ProductsInBasket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.objects.product.Product;

import javax.swing.*;

public class CustomerBasketViewController
{
  //TODO if this approach doesn't work there is another solution.
  @FXML
  private TableView<ProductsInBasket> tableView;

  @FXML
  private TableColumn<String, ProductsInBasket> name;

  @FXML
  private TableColumn<String, ProductsInBasket> priceperunit;

  @FXML
  private TableColumn<String, ProductsInBasket> quantity;

  @FXML
  private TableColumn<String, ProductsInBasket> size;

  @FXML
  private TableColumn<String, ProductsInBasket> totalprice;

  private ViewHandler viewHandler;
  private CustomerBasketViewModel viewModel;

  public void backButton(ActionEvent event)
  {
    viewHandler.openCustomerAllEquipmentView();
  }

  public void onRemoveButton(ActionEvent event)
  {
    if(tableView.getSelectionModel().getSelectedItem() == null)
    {
      return;
    }

    viewModel.removeItemFormBasket(tableView.getSelectionModel().getSelectedItem().getProduct());
  }

  public void onClearButton(ActionEvent event)
  {
    viewModel.clearBasket();
  }

  public void onOrderButton(ActionEvent event)
  {
      viewModel.order();
  }


  public void init(ViewHandler viewHandler, ViewModelFactory vmf)
  {
    this.viewHandler = viewHandler;
    viewModel = vmf.getCustomerBasketViewModel();

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    priceperunit.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
    totalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    tableView.setItems(viewModel.getProductsInBaskets());
    viewModel.showAllProductsInBasket();

  }

  public void showAllProductsInBasket() {
      viewModel.showAllProductsInBasket();
  }



}
