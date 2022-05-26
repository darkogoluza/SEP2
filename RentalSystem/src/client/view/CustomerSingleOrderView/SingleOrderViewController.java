package client.view.CustomerSingleOrderView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.basket.ProductsInBasket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.objects.reservation.ReservationStatus;

public class SingleOrderViewController {
  ObservableList<ReservationStatus> statusList = FXCollections.observableArrayList(
      ReservationStatus.returned,
      ReservationStatus.notReturned,
      ReservationStatus.rented
  );

  private ViewHandler viewHandler;
  private SingleOrderViewModel viewModel;

  @FXML
  private Button back;

  @FXML
  private Label finalTotalPrice;

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
  private Label returndate;

  @FXML
  private TableColumn<?, ?> size;

  @FXML
  private Label status;

  @FXML
  private TableColumn<?, ?> totalprice;
  @FXML
  private TableView<ProductsInBasket> tableView;

  @FXML
  private Label username;

  @FXML
  private Label returnTime;

  @FXML
  private Label totalItemsInBasket;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf, int id) {
    this.viewHandler = viewHandler;
    viewModel = vmf.getSingleOrderViewModel(id);

    orderID.textProperty().bindBidirectional(viewModel.getOrderIdProperty());
    status.textProperty().bindBidirectional(viewModel.getStatusProperty());
    orderdate.textProperty().bind(viewModel.getCreatedAtDateProperty());
    ordertime.textProperty().bind(viewModel.getCreatedAtTimeProperty());
    username.textProperty().bind(viewModel.getUserNameProperty());
    status.textProperty().bindBidirectional(viewModel.getStatusProperty());
    returndate.textProperty().bind(viewModel.getReturnedAtDateProperty());
    returnTime.textProperty().bind(viewModel.getReturnedAtTimeProperty());
    finalTotalPrice.textProperty().bind(viewModel.gettotalOverallPriceProperty());
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    priceperunit.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
    totalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    tableView.setItems(viewModel.getProductsInBaskets());
    totalItemsInBasket.textProperty().bind(viewModel.getTotalItemsInBasketProperty());

    viewModel.showAllProducts();
    viewModel.updateViewModelReservationInfo();
  }

  @FXML
  void backButton(ActionEvent event) {
    viewHandler.openCustomerAllEquipmentView();
  }
  public void onLogOff(ActionEvent event)
  {
    viewModel.logOff();
    viewHandler.openLoginView();
  }
  public void onGoToReservations(ActionEvent event)
  {
    viewHandler.openSingleOrderView();
  }
}
