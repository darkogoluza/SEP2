package client.view.employeeAllOrders;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;

public class EmployeeAllOrdersController {
	@FXML
	private Button searchButton;
	@FXML
	private TextField searchInput;

	private ViewHandler viewHandler;
	private EmployeeAllOrdersViewModel viewModel;

	public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
		this.viewHandler = viewHandler;
		viewModel = vmf.getEmployeeViewModel();

//		searchInput.textProperty().bindBidirectional(viewModel.getSearchProperty());

	}

	public void onSearchButton(ActionEvent event) {
		System.out.println(searchInput.getText());
	}
}
