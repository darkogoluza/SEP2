package client.view.CustomerSingleOrderView;

    import client.core.ViewHandler;
    import client.core.ViewModelFactory;
    import client.view.customerAllEquipment.CustomerAllEquipmentViewModel;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.control.ListView;
    import shared.objects.product.Product;
    import shared.objects.reservation.Reservation;
    import shared.objects.reservation.ReservationList;
    import shared.objects.product.ProductList;


public class SingleOrderViewController {
  ViewHandler viewHandler;
  private SingleOrderViewModel viewModel;
  private ReservationList reservationlist;
  private Reservation reservation;
  private ProductList products;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf)
  {
    this.viewHandler = viewHandler;
    viewModel = vmf.getSingleOrderView();
    username.textProperty().bind(viewModel.getUsername());
    ordertime.textProperty().bind(viewModel.getOrdertime());
    orderdate.textProperty().bind(viewModel.getOrderDate());

    for(int i=1;i<=reservationlist.size();i++)
    if (username.equals(reservation.getUserName())){
      for(int j=0;j<products.size();j++) reservation.getProducts();

  }


  }

  @FXML
  private TableView<Product> product;

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