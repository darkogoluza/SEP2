package client.view.registryView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;




public class RegistryViewController {
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
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
    }

    public void createAccountButton(ActionEvent event)
    {
        viewModel.createAccount();
        //viewHandler.openCustomerAllEquipmentView();
    }


}
