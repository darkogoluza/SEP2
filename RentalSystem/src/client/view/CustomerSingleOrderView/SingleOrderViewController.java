package client.view.CustomerSingleOrderView;

    import client.core.ViewHandler;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TextField;

public class SingleOrderViewController {
ViewHandler viewHandler;
  @FXML
  private Button back;

  @FXML
  private TableColumn<?, ?> name;

  @FXML
  private Label orderID;

  @FXML
  private Label orderdate;

  @FXML
  private Label ordertime;

  @FXML
  private TableColumn<?, ?> priceperunit;

  @FXML
  private TableColumn<?, ?> quantity;

  @FXML
  private TableColumn<?, ?> size;

  @FXML
  private TableColumn<?, ?> totalprice;

  @FXML
  private Label username;

  @FXML
  void ShowOrderID(ActionEvent event) {

  }

  @FXML
  void ShowUsername(ActionEvent event) {

  }

  public void BackButton(ActionEvent event)
  {
    viewHandler.openCustomerAllEquipmentView();
  }



}