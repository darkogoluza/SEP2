package administrator.view.administratorView;

import administrator.core.ViewHandler;
import administrator.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


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

    }

    public void addButtonAdministrator(ActionEvent event)
    {

    }

    public void removeButtonAdministrator(ActionEvent event)
    {

    }

    public void editButtonAdministrator(ActionEvent event)
    {

    }



}
