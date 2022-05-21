package client.view.customerAllOrdersView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.objects.reservation.Reservation;

public class CustomerAllOrdersController
{
  @FXML
  private Button logOffButton;
  @FXML
  private Button searchButton;
  @FXML
  private Button goBackButton;
  @FXML
  private Button openReservationButton;
  @FXML
  private TextField searchInput;
  @FXML
  private ListView reservationsList;

  private ViewHandler viewHandler;
  private client.view.customerAllOrdersView.CustomerAllOrdersViewModel viewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
    this.viewHandler = viewHandler;
    viewModel = vmf.getCustomerAllOrdersViewModel();

    reservationsList.itemsProperty().bindBidirectional(viewModel.getListOfReservationsProperty());
    searchInput.textProperty().bindBidirectional(viewModel.getSearchProperty());
  }

  public void onSearchButton(ActionEvent event) {
    int id = Integer.parseInt(searchInput.getText());
    if (id >= 0 && id <= viewModel.reservationsCount()) {
      Reservation r = viewModel.openReservationById(id);
      if (r != null) {
        viewHandler.openEmployeeOrderDetailsView(r.getId());
      }
      else {
        //error
      }
    }
    else {
      //error
    }
  }

  public void onLogOff() {
    viewHandler.openLoginView();
  }

  public void onOpenReservation() {
    if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
      return;

    int id = viewModel.openReservationByIndex(reservationsList.getSelectionModel().getSelectedIndex());

    viewHandler.openEmployeeOrderDetailsView(id);
  }
  public void onGoBack()
  {
    viewHandler.openCustomerBasket();
    // TODO: 5/21/2022 from where do we go to customer all orders? where should it go back? 
  }
}
