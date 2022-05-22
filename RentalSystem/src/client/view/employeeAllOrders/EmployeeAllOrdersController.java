package client.view.employeeAllOrders;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.objects.reservation.ReservationStatus;

public class EmployeeAllOrdersController {
	ObservableList<String> statuses = FXCollections.observableArrayList(
			ReservationStatus.notReturned.toString(),
			ReservationStatus.rented.toString(),
			ReservationStatus.returned.toString(),
			"All"
	);

	@FXML
	private Button searchButton;
	@FXML
	private Button logOffButton;
	@FXML
	private TextField searchInput;
	@FXML
	private ListView reservationsList;
	@FXML
	private ChoiceBox<String> filterByStatus;

	private ViewHandler viewHandler;
	private client.view.employeeAllOrders.EmployeeAllOrdersViewModel viewModel;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeViewModel();

		filterByStatus.setItems(statuses);
		filterByStatus.setValue(ReservationStatus.notReturned.toString());

		reservationsList.itemsProperty().bindBidirectional(viewModel.getListOfReservationsProperty());
		searchInput.textProperty().bindBidirectional(viewModel.getSearchProperty());
	}

	public void onSearchButton(ActionEvent event) {
		int id = Integer.parseInt(searchInput.getText());
		if (id >= 0 && id <= viewModel.reservationsCount()) {
			id = viewModel.openReservationById(id);
			viewHandler.openEmployeeOrderDetailsView(id);
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

	public void onRemoveReservation(){
		if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
			return;
		else
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Confirm that you want to remove this order, you won't be able to change this decision later!");

			alert.showAndWait();
			viewModel.removeReservation(reservationsList.getSelectionModel().getSelectedIndex());

		}
	}

	public void onFilterChoiceChanged(ActionEvent event) {
		viewModel.changedFilterStatus(filterByStatus.getValue());
	}
}
