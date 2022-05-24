package client.view.employeeAllOrders;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.objects.errors.AlertHandler;
import shared.objects.reservation.Reservation;

public class EmployeeAllOrdersController {
	@FXML
	private Button searchButton;
	@FXML
	private Button logOffButton;
	@FXML
	private TextField searchInput;
	@FXML
	private ListView reservationsList;

	private ViewHandler viewHandler;
	private client.view.employeeAllOrders.EmployeeAllOrdersViewModel viewModel;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeViewModel();

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
				AlertHandler.getInstance().orderDontExist();
			}
		}
		else {
			AlertHandler.getInstance().orderDontExist();
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

	public void onRemoveReservation(ActionEvent event){
		if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
			return;
		else
		{
			if (AlertHandler.getInstance().onRemoveReservation(event))
				viewModel.removeReservation(reservationsList.getSelectionModel().getSelectedIndex());
		}
	}
}
