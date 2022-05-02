package client.view.administratorView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.objects.Product;


public class AdministratorViewController {

    @FXML
    private TextField idTextFieldAdministrator;
    @FXML
    private ChoiceBox typeChoiceBoxAdministrator;
    @FXML
    private ChoiceBox colorChoiceBoxAdministrator;
    @FXML
    private TextField sizeTextFieldAdministrator;
    @FXML
    private Button addButtonAdministrator;
    @FXML
    private Button removeButtonAdministrator;
    @FXML
    private Button editButtonAdministrator;
    @FXML
    private ListView listViewAdministrator;

    private ViewHandler viewHandler;
    private AdministratorViewModel administratorViewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf)
    {
        this.viewHandler = viewHandler;
		administratorViewModel = vmf.getAdministratorViewModel();

    }

    public void addButtonAdministrator(ActionEvent event)
    {
		int id =
		Product product = new Product()
		administratorViewModel.addProduct();
    }

    public void removeButtonAdministrator(ActionEvent event)
    {
		administratorViewModel.removeProduct(Integer.parseInt(idTextFieldAdministrator.getText()));
    }

    public void editButtonAdministrator(ActionEvent event)
    {

    }



}
