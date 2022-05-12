package client.view.CustomerBasketView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class CustomerBasketViewController
{
  //TODO if this approach doesn't work there is another solution.
  @FXML
  private TableColumn<?, ?> name;

  @FXML
  private TableColumn<?, ?> priceperunit;

  @FXML
  private TableColumn<?, ?> quantity;

  @FXML
  private TableColumn<?, ?> size;

  @FXML
  private TableColumn<?, ?> totalprice;

  private ViewHandler viewHandler;
  private CustomerBasketViewModel viewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
    this.viewHandler = viewHandler;
    viewModel = vmf.getCustomerBasketViewModel();

  }

  public void BackButton(ActionEvent event)
  {
    //open new view
  }



}
