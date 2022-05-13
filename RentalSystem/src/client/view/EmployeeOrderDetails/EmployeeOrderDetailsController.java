package client.view.EmployeeOrderDetails;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import shared.objects.reservation.ReservationStatus;

public class EmployeeOrderDetailsController {
	ObservableList<ReservationStatus> statusList = FXCollections.observableArrayList(
			ReservationStatus.returned,
			ReservationStatus.notReturned,
			ReservationStatus.rented
	);

	private ViewHandler viewHandler;
	private EmployeeOrderDetailsViewModel viewModel;

	@FXML
	private Button back;

	@FXML
	private Button changeStatus;

	@FXML
	private Label finaltotalprice;

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
	private ChoiceBox<ReservationStatus> statusChoice;

	@FXML
	private TableColumn<?, ?> totalprice;

	@FXML
	private Label username;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeOrderDetailsViewModel();

		orderID.textProperty().bindBidirectional(viewModel.getOrderIdProperty());
		status.textProperty().bindBidirectional(viewModel.getStatusProperty());

		statusChoice.setItems(statusList);
		statusChoice.setValue(ReservationStatus.rented);

	}

	@FXML
	void BackButton(ActionEvent event) {
		viewHandler.openEmployeeView();
	}

	@FXML
	void onChangeStatus(ActionEvent event) {
		viewModel.changeStatus(Integer.parseInt(orderID.getText()), statusChoice.getValue());
	}

}
