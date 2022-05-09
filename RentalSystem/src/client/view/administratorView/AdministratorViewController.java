package client.view.administratorView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.objects.*;

public class AdministratorViewController {
    ObservableList<String> equipmentTypeList = FXCollections.observableArrayList(
            EquipmentType.helmet.toString(),
            EquipmentType.ski.toString(),
            EquipmentType.skiPoles.toString(),
            EquipmentType.snowboard.toString(),
            EquipmentType.snowboardShoes.toString()
    );

    ObservableList<String> colorTypeList = FXCollections.observableArrayList(
            Color.red.toString(),
            Color.black.toString(),
            Color.blue.toString(),
            Color.pink.toString(),
            Color.green.toString(),
            Color.white.toString()
    );

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
    @FXML
    private TextField priceTextField;

    private ViewHandler viewHandler;
    private AdministratorViewModel administratorViewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf)
    {
        this.viewHandler = viewHandler;
		administratorViewModel = vmf.getAdministratorViewModel();

		typeChoiceBoxAdministrator.setValue(EquipmentType.helmet.toString());
		typeChoiceBoxAdministrator.setItems(equipmentTypeList);

		colorChoiceBoxAdministrator.setValue(Color.red.toString());
		colorChoiceBoxAdministrator.setItems(colorTypeList);

		listViewAdministrator.itemsProperty().bind(administratorViewModel.getListViewAdministrator());
    }

    public void addButtonAdministrator(ActionEvent event)
    {
		Product product = new Product(Integer.parseInt(
		        idTextFieldAdministrator.getText()),
                Double.parseDouble(priceTextField.getText()),
                Color.valueOf(colorChoiceBoxAdministrator.getValue().toString()),
                EquipmentType.valueOf(typeChoiceBoxAdministrator.getValue().toString()),
                new LabelFormat(sizeTextFieldAdministrator.getText())
        );

		administratorViewModel.addProduct(product);
    }

    public void removeButtonAdministrator(ActionEvent event)
    {
		administratorViewModel.removeProduct(Integer.parseInt(idTextFieldAdministrator.getText()));
    }

    public void editButtonAdministrator(ActionEvent event)
    {

    }

}
