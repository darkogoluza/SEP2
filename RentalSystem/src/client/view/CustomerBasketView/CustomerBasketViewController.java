package client.view.CustomerBasketView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class CustomerBasketViewController
{

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

  public void BackButton(ActionEvent event)
  {
    //open new view
  }



}
