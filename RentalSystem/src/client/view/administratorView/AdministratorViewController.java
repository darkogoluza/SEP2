package client.view.administratorView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import shared.objects.*;

public class AdministratorViewController {
    ObservableList<String> equipmentTypeList = FXCollections.observableArrayList(
            EquipmentType.helmet.toString(),
            EquipmentType.ski.toString(),
            EquipmentType.skiPoles.toString(),
            EquipmentType.snowboard.toString(),
            EquipmentType.skiShoes.toString(),
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
    private ChoiceBox typeChoiceBox;
    @FXML
    private ChoiceBox colorChoiceBox;
    @FXML
    private TextField sizeTextField;
    @FXML
    private Label sizeLabel;
    @FXML
    private ListView listView;
    @FXML
    private TextField priceTextField;

    @FXML
    private HBox normalButtons;
    @FXML
    private HBox editButtons;

    private ViewHandler viewHandler;
    private AdministratorViewModel viewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf)
    {
        this.viewHandler = viewHandler;
		viewModel = vmf.getAdministratorViewModel();

		sizeTextField.textProperty().bindBidirectional(viewModel.getSizeStringProperty());
		priceTextField.textProperty().bindBidirectional(viewModel.getPriceStringProperty());

		typeChoiceBox.setValue(EquipmentType.helmet.toString());
		typeChoiceBox.setItems(equipmentTypeList);
        sizeLabel.setText("Label Values S to XXL");

        typeChoiceBox.valueProperty().addListener((obs, old, niu)->{
            if (EquipmentType.helmet.equals(EquipmentType.valueOf((String) niu))) {
                sizeLabel.setText("Label Values S to XXL");
            } else if (EquipmentType.ski.equals(EquipmentType.valueOf((String) niu))) {
                sizeLabel.setText("Height");
            } else if (EquipmentType.skiPoles.equals(EquipmentType.valueOf((String) niu))) {
                sizeLabel.setText("Height");
            }else if (EquipmentType.snowboard.equals(EquipmentType.valueOf((String) niu))) {
                sizeLabel.setText("Height");
            }else if (EquipmentType.skiShoes.equals(EquipmentType.valueOf((String) niu))) {
                sizeLabel.setText("Label Values 35 to 45");
            }else if (EquipmentType.snowboardShoes.equals(EquipmentType.valueOf((String) niu))) {
                sizeLabel.setText("Label Values 35 to 35");
            }
    });

		colorChoiceBox.setValue(Color.red.toString());
		colorChoiceBox.setItems(colorTypeList);

		listView.itemsProperty().bind(viewModel.getListViewAdministrator());

		isEdit(false);
    }

    public void addButton(ActionEvent event)
    {
        if(!isInputOkay()){
            showWrongInputDialog();
            return;
        }

        viewModel.addProduct(Double.parseDouble(priceTextField.getText()),
                Color.valueOf(colorChoiceBox.getValue().toString()),
                EquipmentType.valueOf(typeChoiceBox.getValue().toString()),
                getSize());

        viewModel.clearFields();
    }

    public void removeButton(ActionEvent event)
    {
        if(listView.getSelectionModel().getSelectedIndex() < 0)
            return;

		viewModel.removeProduct(listView.getSelectionModel().getSelectedIndex());
    }

    public void editButton(ActionEvent event)
    {
        if(listView.getSelectionModel().getSelectedIndex() < 0)
            return;

        isEdit(true);
        viewModel.setFieldsTo();
    }

    public void candleEdit() {
        isEdit(false);
    }

    public void changeProduct() {
        isEdit(false);
    }

    private void isEdit(boolean b) {
        normalButtons.setVisible(!b);
        editButtons.setVisible(b);
    }

    private Size getSize() {
        Size size;
        if(isLabelFormat()) {
            size = new LabelFormat(sizeTextField.getText().toUpperCase());
        } else {
            size = new MetricFormat(Double.parseDouble(sizeTextField.getText()));
        }

        return size;
    }

    private boolean isInputOkay() {
        try{
            Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException e) {
            return false;
        }

        if(!isLabelFormat()) {
            try {
                Double.parseDouble(sizeTextField.getText());
            } catch (NumberFormatException e) {
                return false;
            }
        } else if(typeChoiceBox.getValue().toString().equals(EquipmentType.helmet.toString())) {
            String value = sizeTextField.getText().toUpperCase();

            if(!value.equals("S") && !value.equals("M") && !value.equals("L") && !value.equals("XL") && !value.equals("XXL") && !value.equals("XXXL")){
                return false;
            }
        } else {
            try {
                int i = Integer.parseInt(sizeTextField.getText());
                if(!(i >= 35 && i <= 45)){
                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private boolean isLabelFormat() {
        return typeChoiceBox.getValue().toString().equals(EquipmentType.helmet.toString()) ||
                typeChoiceBox.getValue().toString().equals(EquipmentType.skiShoes.toString()) ||
                typeChoiceBox.getValue().toString().equals(EquipmentType.snowboardShoes.toString());
    }

    private void showWrongInputDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You must have entered wrong input");
        alert.setContentText("Price needs to be a number.\nLabel size need to be one of [\"S\",\"M\",\"L\",\"XL\",\"XXL\",\"XXXL\"]\nHeight size must be a number.\nLabel for shoes is a number between 35 and 45. ");

        alert.showAndWait();
    }
}
