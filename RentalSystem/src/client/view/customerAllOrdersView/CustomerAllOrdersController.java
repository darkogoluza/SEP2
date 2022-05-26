package client.view.customerAllOrdersView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.objects.errors.AlertHandler;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationStatus;

public class CustomerAllOrdersController
{
  ObservableList<String> statuses = FXCollections.observableArrayList(
      ReservationStatus.notReturned.toString(),
      ReservationStatus.rented.toString(),
      ReservationStatus.returned.toString(),
      "All"
  );
  @FXML
  private TextField searchInput;
  @FXML
  private Label username;
  @FXML
  private Label totalItemsInBasket;
  @FXML
  private Button gotToBasketButton;
  @FXML
  private Button logOffButton;
  @FXML
  private Button searchButton;
  @FXML
  private Button openReservationButton;
  @FXML
  private ListView reservationsList;

  private ViewHandler viewHandler;
  private client.view.customerAllOrdersView.CustomerAllOrdersViewModel viewModel;


  public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
    this.viewHandler = viewHandler;
    viewModel = vmf.getCustomerAllOrdersViewModel();

    username.textProperty().bind(viewModel.getUserNameProperty());
    totalItemsInBasket.textProperty().bind(viewModel.getTotalItemsInBasketProperty());
    reservationsList.itemsProperty().bindBidirectional(viewModel.getListOfReservationsProperty());
    searchInput.textProperty().bindBidirectional(viewModel.getSearchProperty());
  }
  @FXML
  public void onSearchButton(ActionEvent event) {
    int id = -1;
    try {
      id = Integer.parseInt(searchInput.getText());
    } catch (NumberFormatException e) {
      AlertHandler.getInstance().wrongOrderIdInput();
      return;
    }

    Reservation r = viewModel.openReservationById(id);
    if (r != null) {
      viewHandler.openSingleOrderView(r.getId());
    }
    else {
      AlertHandler.getInstance().orderDontExist();
    }

  }
  @FXML void backButton(ActionEvent event) {
    viewHandler.openCustomerAllEquipmentView();
  }
  public void onLogOff() {
    viewModel.logOff();
    viewHandler.openLoginView();
  }
  public void onOpenReservation() {
    if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
      return;

    int id = viewModel.openReservationByIndex(reservationsList.getSelectionModel().getSelectedIndex());

    viewHandler.openSingleOrderView(id);
  }
  public void onGoToBasketButton(ActionEvent event)
  {
    viewHandler.openCustomerBasket();
  }

  public void onGoToReservations(ActionEvent event)
  {
    viewHandler.openCustomerAllOrdersView();
  }
}
