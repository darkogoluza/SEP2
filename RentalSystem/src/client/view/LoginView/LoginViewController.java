package client.view.LoginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.EmployeeOrderDetails.EmployeeOrderDetailsViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BackgroundFill;
import shared.objects.reservation.ReservationStatus;

public class LoginViewController
{
  private ViewHandler viewHandler;
  private LoginViewModel viewModel;

  @FXML
  private TextField userName;

  @FXML
  private TextField password;

  @FXML
  private Button logInButton;

  @FXML
  private Button clickHere;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf, int id)
  {
    this.viewHandler = viewHandler;
//    viewModel = vmf.getLoginViewModel(id);
    userName.textProperty().bindBidirectional(viewModel.getUserNameProperty());
    password.textProperty().bindBidirectional(viewModel.getUserNameProperty());

  }
    @FXML
    public void onUserNameInput() {
      viewModel.checkUserName(userName.getText());
    }

    @FXML
    void onPasswordInput() {
      viewModel.checkPassword(password.getText());
    }

    @FXML
    void logInToAccount() {
//    if statements
      viewHandler.openCustomerAllEquipmentView();
    }

    @FXML
    void createAccount() {
      viewHandler.openRegistryView();
    }

}

