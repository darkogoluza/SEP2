package client.view.CustomerBasketView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.objects.product.Product;

public class CustomerBasketViewController
{
  //TODO if this approach doesn't work there is another solution.
  @FXML
  private TableView<Product> tableView;

  @FXML
  private TableColumn<String, Product> name;

  @FXML
  private TableColumn<String, Product> priceperunit;

  @FXML
  private TableColumn<String, Product> quantity;

  @FXML
  private TableColumn<String, Product> size;

  @FXML
  private TableColumn<String, Product> totalprice;

  private ViewHandler viewHandler;
  private CustomerBasketViewModel viewModel;

  public void BackButton(ActionEvent event)
  {
    viewHandler.openCustomerAllEquipmentView();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory vmf)
  {

    this.viewHandler = viewHandler;
    viewModel = vmf.getCustomerBasketView();

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    priceperunit.setCellValueFactory(new PropertyValueFactory<>("price per unit"));
    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
    totalprice.setCellValueFactory(new PropertyValueFactory<>("total price"));

  }


}
