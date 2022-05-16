package client.view.CustomerSingleOrderView;

    import client.core.ViewHandler;
    import client.core.ViewModelFactory;
    import client.model.basket.ProductsInBasket;
    import client.view.customerAllEquipment.CustomerAllEquipmentViewModel;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.control.ListView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import shared.objects.product.Product;
    import shared.objects.reservation.Reservation;
    import shared.objects.reservation.ReservationList;
    import shared.objects.product.ProductList;
    import client.model.basket.ProductsInBasket;

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
    ordertime.textProperty().bindBidirectional(viewModel.getOrdertime());
    orderdate.textProperty().bindBidirectional(viewModel.getOrderDate());
    returndate.textProperty().bindBidirectional(viewModel.getReturnDate());
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    priceperunit.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
    totalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    finalTotalPrice.textProperty().bind(viewModel.getFinalTotalPriceProperty());

    tableView.setItems(viewModel.getProductsInBaskets());
    viewModel.showAllProductsInBasket();
  }


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


  @FXML
  private Label username;

  @FXML
  private Label returndate;

  @FXML
  private Label status;

  @FXML
  private Label orderID;

  @FXML
  private Label orderdate;

  @FXML
  private Label ordertime;


  @FXML
  private Button back;

  @FXML
  private Label finalTotalPrice;

  public void BackButton(ActionEvent event)
  {
    viewHandler.openCustomerAllEquipmentView();
  }

  public void showAllProductsInBasket() {
    viewModel.showAllProductsInBasket();
  }

}