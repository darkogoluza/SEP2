package client.view.employeeAllOrders;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.*;

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
	private EmployeeAllOrdersViewModel viewModel;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeViewModel();

		reservationsList.itemsProperty().bindBidirectional(viewModel.getListOfReservationsProperty());
		searchInput.textProperty().bindBidirectional(viewModel.getSearchProperty());

	}

	public void onSearchButton(ActionEvent event) {
		int id = Integer.parseInt(searchInput.getText());
		if (id >= 0 && id < viewModel.reservationsCount())
			viewModel.openReservationById(Integer.parseInt(searchInput.getText()));
		else {
			//error

		}

	}

	public void onLogOff() {

	}

	public void onOpenReservation() {
		if(reservationsList.getSelectionModel().getSelectedIndex() < 0)
			return;

		viewModel.openReservationByIndex(reservationsList.getSelectionModel().getSelectedIndex());
		viewHandler.openSingleOrderView();
	}
}
