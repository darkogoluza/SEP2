package client.view.administratorView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shared.objects.errors.AlertHandler;
import shared.objects.product.*;

import java.io.File;
import java.net.MalformedURLException;

/**
 Controller for administrator view
 */
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
    private TextField amountTextFiled;

    @FXML
    private HBox normalButtons;
    @FXML
    private HBox editButtons;

    private ViewHandler viewHandler;
    private AdministratorViewModel viewModel;

    private int currentEditingProductIndex = 0;

    /**
     * constructor open together with view,it is accessing size, price, amount, color, type and list of administrators
     through methods in AdministratorviewModel
     * @param viewHandler
     * @param vmf
     */
    public void init(ViewHandler viewHandler, ViewModelFactory vmf)
    {
        this.viewHandler = viewHandler;
		viewModel = vmf.getAdministratorViewModel();

		sizeTextField.textProperty().bindBidirectional(viewModel.getSizeStringProperty());
		priceTextField.textProperty().bindBidirectional(viewModel.getPriceStringProperty());
		amountTextFiled.textProperty().bindBidirectional(viewModel.getAmountStringProperty());

		typeChoiceBox.setItems(equipmentTypeList);
        typeChoiceBox.valueProperty().bindBidirectional(viewModel.getType());
        typeChoiceBox.setValue(EquipmentType.helmet.toString());
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
                sizeLabel.setText("Label Values 35 to 45");
            }
    });

		colorChoiceBox.setItems(colorTypeList);
		colorChoiceBox.valueProperty().bindBidirectional(viewModel.getColor());
        colorChoiceBox.setValue(Color.red.toString());

		listView.itemsProperty().bind(viewModel.getListViewAdministrator());

		isEdit(false);

		viewModel.loadData();
    }

    /**
     * Method add product with all set variables into database after clicking addButton event
     * @param event
     */
    public void addButton(ActionEvent event)
    {
        if(!isInputOkay()){
			AlertHandler.getInstance().administratorWrongInput();
			return;
        }

        viewModel.addProduct(Double.parseDouble(priceTextField.getText()),
                Color.valueOf(colorChoiceBox.getValue().toString()),
                EquipmentType.valueOf(typeChoiceBox.getValue().toString()),
                getSize(),
                Integer.parseInt(amountTextFiled.getText())
        );

        viewModel.clearFields();
    }

    /**
     * Method change product after its edited
     * @return WARNING when wrong input is inserted
     */
    public void changeProduct() {
        if(!isInputOkay()){
            AlertHandler.getInstance().administratorWrongInput();
            return;
        }

        typeChoiceBox.setDisable(false);
        isEdit(false);
        viewModel.changeProduct(currentEditingProductIndex,
                Double.parseDouble(priceTextField.getText()),
                Color.valueOf(colorChoiceBox.getValue().toString()),
                getSize(),
                Integer.parseInt(amountTextFiled.getText())
        );
        viewModel.clearFields();
    }

    /**
     * Method remove selected product after clicking removeButton
     * @param event
     */
    public void removeButton(ActionEvent event)
    {
        if(listView.getSelectionModel().getSelectedIndex() < 0)
            return;

		if (AlertHandler.getInstance().onRemoveProduct())
			viewModel.removeProduct(listView.getSelectionModel().getSelectedIndex());
    }

    /**
     * Method allows administrator to edit selected product specifications after clicking button
     * @param event
     */
    public void editButton(ActionEvent event)
    {
        if(listView.getSelectionModel().getSelectedIndex() < 0)
            return;

        currentEditingProductIndex = listView.getSelectionModel().getSelectedIndex();
        isEdit(true);
        viewModel.setFieldsTo(currentEditingProductIndex);
        typeChoiceBox.setDisable(true);

    }

    /**
     * Method cancel edit fields when product is edited and changed or the change is canceled
     */
    public void cancelEdit() {
        typeChoiceBox.setDisable(false);
        viewModel.clearFields();
        isEdit(false);
    }

    /**
     * Checks if product is being edited
     * @param b
     */
    private void isEdit(boolean b) {
        normalButtons.setVisible(!b);
        editButtons.setVisible(b);
    }

    /**
     * Method converts size input from Label to uppercase and changing type to double
     */
    private Size getSize() {
        Size size;
        if(isLabelFormat()) {
            size = new LabelFormat(sizeTextField.getText().toUpperCase());
        } else {
            size = new MetricFormat(Double.parseDouble(sizeTextField.getText()));
        }

        return size;
    }

    /**
     * Method is checking if input was written correctly
     */
    private boolean isInputOkay() {
        try{
            Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        try {
            Integer.parseInt(amountTextFiled.getText());
        } catch (NumberFormatException  | NullPointerException e) {
            return false;
        }

        if(!isLabelFormat()) {
            try {
                Double.parseDouble(sizeTextField.getText());
            } catch (NumberFormatException | NullPointerException e) {
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

    /**
     * Method checks if the given input is LabelFormat for product
     */
    private boolean isLabelFormat() {
        return typeChoiceBox.getValue().toString().equals(EquipmentType.helmet.toString()) ||
                typeChoiceBox.getValue().toString().equals(EquipmentType.skiShoes.toString()) ||
                typeChoiceBox.getValue().toString().equals(EquipmentType.snowboardShoes.toString());
    }

    /**
     * log out user from application
     */
	@FXML
	public void onLogOff() {
		viewModel.logOff();
		viewHandler.openLoginView();
	}

    /**
     * Method allows administrator to add image to product
     */
	public void browseFiles() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open File");

		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files",
						"*.png", "*.jpg", "*.jpeg"
				));

		File file = fc.showOpenDialog(new Stage());

		viewModel.addFile(file);

	}

    /**
     * open registry window
     */
	public void onCreateAccount()
    {
        viewHandler.openRegistryView();
    }
}
