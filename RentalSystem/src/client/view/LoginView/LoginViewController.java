package client.view.LoginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BackgroundFill;
import shared.objects.errors.AlertHandler;
import shared.objects.product.EquipmentType;
import shared.objects.reservation.ReservationStatus;
import shared.objects.user.UserRole;

public class LoginViewController
{
  private ViewHandler viewHandler;
  private LoginViewModel viewModel;

  @FXML
  private TextField userName;

  @FXML
  private PasswordField password;

  @FXML
  private Button logInButton;

  @FXML
  private Button createAccount;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf)
  {
    this.viewHandler = viewHandler;
    viewModel = vmf.getLoginViewModel();
    userName.textProperty().bindBidirectional(viewModel.getUserNameProperty());
    password.textProperty().bindBidirectional(viewModel.getPasswordProperty());

    viewModel.clearFields();
  }

  @FXML
	void logInToAccount() {
		boolean isLogged = viewModel.checkIdentification(userName.getText(),password.getText());

		if (isLogged) {
			UserRole role = viewModel.getRoleOfUser();

			if (role == UserRole.admin)
				viewHandler.openAdministratorView();
			else if(role == UserRole.customer)
				viewHandler.openCustomerAllEquipmentView();
			else if(role == UserRole.employee)
				viewHandler.openEmployeeView();

            viewModel.clearFields();
		}
		else {
			AlertHandler.getInstance().wrongCredentials();
		}

	}

    @FXML
    void createAccount() {
      viewHandler.openRegistryView();
      viewModel.clearFields();
    }
}

