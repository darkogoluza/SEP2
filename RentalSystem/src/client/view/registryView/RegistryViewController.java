package client.view.registryView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistryViewController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField phoneNumber;

    private ViewHandler viewHandler;
    private RegistryViewModel viewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
        this.viewHandler = viewHandler;
        viewModel = vmf.getRegistryViewModel();

        userName.textProperty().bindBidirectional(viewModel.getUserName());
        password.textProperty().bindBidirectional(viewModel.getPassword());
        confirmPassword.textProperty().bindBidirectional(viewModel.getConfirmedPassword());
        phoneNumber.textProperty().bindBidirectional(viewModel.getPhoneNumber());

        viewModel.clearFields();
    }

    public void createAccountButton(ActionEvent event)
	{
        if (viewModel.createAccount()) {
            viewHandler.openCustomerAllEquipmentView();
            viewModel.clearFields();
        }
    }

	public void openLoginView() {
		viewHandler.openLoginView();
		viewModel.clearFields();
	}

}
