package client.view.EmployeeOrderDetails;

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
	private TableView<ProductsInBasket> tableView;

	@FXML
	private Label username;

	@FXML
	private Label returnTime;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeOrderDetailsViewModel();

		orderID.textProperty().bindBidirectional(viewModel.getOrderIdProperty());
		status.textProperty().bindBidirectional(viewModel.getStatusProperty());
		orderdate.textProperty().bind(viewModel.getCreatedAtDateProperty());
		ordertime.textProperty().bind(viewModel.getCreatedAtTimeProperty());
		username.textProperty().bind(viewModel.getUserNameProperty());
		status.textProperty().bind(viewModel.getStatusProperty());
		returndate.textProperty().bind(viewModel.getReturnedAtDateProperty());
		returnTime.textProperty().bind(viewModel.getReturnedAtTimeProperty());


		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceperunit.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		size.setCellValueFactory(new PropertyValueFactory<>("size"));
		totalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		tableView.setItems(viewModel.getProductsInBaskets());

		statusChoice.setItems(statusList);
		statusChoice.setValue(ReservationStatus.rented);
		viewModel.showAllProducts();
		viewModel.updateViewModelReservationInfo();
	}

	@FXML
	void BackButton(ActionEvent event) {
		viewHandler.openEmployeeView();
	}

	@FXML
	void onChangeStatus(ActionEvent event) {
		viewModel.changeStatus(Integer.parseInt(orderID.getText()), ReservationStatus.valueOf(status.getText()));
	}




}
