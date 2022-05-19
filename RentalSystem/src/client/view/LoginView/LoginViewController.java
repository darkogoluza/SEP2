package client.view.LoginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.EmployeeOrderDetails.EmployeeOrderDetailsViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

  }

  @FXML
    void openAccount(){
    if(logInToAccount()==true){
      viewHandler.openCustomerAllEquipmentView();
    }

    else{
      System.out.println("User is not registered.");
    }
  }

  @FXML
    boolean logInToAccount() {
    boolean isRegistered=viewModel.checkIdentification(userName.getText(),password.getText());
    return isRegistered;
    }

    @FXML
    void createAccount() {
      viewHandler.openRegistryView();
    }

}

