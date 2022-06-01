package client.view.employeeAllOrders;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.objects.reservation.ReservationStatus;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.objects.errors.AlertHandler;
import shared.objects.reservation.Reservation;

/**
 Controller for employee all orders
 */
public class EmployeeAllOrdersController {
	ObservableList<String> statuses = FXCollections.observableArrayList(
			ReservationStatus.notReturned.toString(),
			ReservationStatus.rented.toString(),
			ReservationStatus.returned.toString(),
			"All"
	);

	@FXML
	private TextField searchInput;
	@FXML
	private ListView reservationsList;
	@FXML
	private ChoiceBox<String> filterByStatus;

	private ViewHandler viewHandler;
	private client.view.employeeAllOrders.EmployeeAllOrdersViewModel viewModel;

	/**
	 * Initialization
	 * @param viewHandler
	 * @param vmf
	 */
	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeViewModel();

		filterByStatus.setItems(statuses);
		filterByStatus.setValue(ReservationStatus.rented.toString());

		reservationsList.itemsProperty().bindBidirectional(viewModel.getListOfReservationsProperty());
		searchInput.textProperty().bindBidirectional(viewModel.getSearchProperty());
	}

	/**
	 * On search button, get id and open reservation with that id
	 * @param event
	 */
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
			viewHandler.openEmployeeOrderDetailsView(r.getId());
		}
		else {
			AlertHandler.getInstance().orderDoNotExist();
		}

	}

	/**
	 * Logout user
	 */
	public void onLogOff() {
		viewModel.logOff();
		viewHandler.openLoginView();
	}

	/**
	 * open selected reservation
	 */
	public void onOpenReservation() {
		if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
			return;

		int id = viewModel.openReservationByIndex(reservationsList.getSelectionModel().getSelectedIndex());

		viewHandler.openEmployeeOrderDetailsView(id);
	}

	/**
	 * remove reservation
	 * @param event
	 */
	public void onRemoveReservation(ActionEvent event){
		if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
			return;
		else
		{
			if (AlertHandler.getInstance().onRemoveReservation(event))
				viewModel.removeReservation(reservationsList.getSelectionModel().getSelectedIndex());
		}
	}

	/**
	 * When category is changed in choice box
	 * @param event
	 */
	public void onFilterChoiceChanged(ActionEvent event) {
		viewModel.changedFilterStatus(filterByStatus.getValue());
	}

	/**
	 * on all orders button
	 * @param event
	 */
	public void onAllOrders(ActionEvent event)
	{
		viewHandler.openEmployeeView();
	}
}
