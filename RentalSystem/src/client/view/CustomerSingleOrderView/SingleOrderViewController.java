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
  private TableColumn<?, ?> name;

  @FXML
  private TableColumn<?, ?> size;

  @FXML
  private TableColumn<?, ?> priceperunit;

  @FXML
  private TableColumn<?, ?> quantity;


  @FXML
  private TableColumn<?, ?> totalprice;

  @FXML
  private Label username;

  @FXML
  private Label orderID;

  @FXML
  private Label orderdate;

  @FXML
  private Label ordertime;


  @FXML
  private Button back;

  public void BackButton(ActionEvent event)
  {
    viewHandler.openCustomerAllEquipmentView();
  }



}