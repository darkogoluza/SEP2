package client.view.CustomerBasketView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.basket.ProductsInBasket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

/**
 Controller for CustomerBasketView
 */
public class CustomerBasketViewController
{

  @FXML
  private TableView<ProductsInBasket> tableView;

  @FXML
  private TableColumn<String, ProductsInBasket> name;

  @FXML
  private TableColumn<String, ProductsInBasket> priceperunit;

  @FXML
  private TableColumn<String, ProductsInBasket> quantity;

  @FXML
  private TableColumn<String, ProductsInBasket> size;

  @FXML
  private TableColumn<String, ProductsInBasket> totalprice;

  @FXML
  private Label userName;
  @FXML
  private Label finalTotalPrice;

  @FXML
  private DatePicker createDate;
  @FXML
  private DatePicker returnDate;
  @FXML
  private Label totalItemsInBasket;

  private ViewHandler viewHandler;
  private CustomerBasketViewModel viewModel;

  public void backButton(ActionEvent event)
  {
    viewHandler.openCustomerAllEquipmentView();
  }

  public void onRemoveButton(ActionEvent event)
  {
    if(tableView.getSelectionModel().getSelectedItem() == null)
    {
      return;
    }

    viewModel.removeItemFormBasket(tableView.getSelectionModel().getSelectedItem().getProduct());
  }

  public void onClearButton(ActionEvent event)
  {
    viewModel.clearBasket();
  }

  public void onOrderButton(ActionEvent event)
  {
      viewModel.order();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory vmf)
  {
    this.viewHandler = viewHandler;
    viewModel = vmf.getCustomerBasketViewModel();

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    priceperunit.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
    totalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    finalTotalPrice.textProperty().bind(viewModel.getFinalTotalPriceProperty());
    userName.textProperty().bind(viewModel.getUserNameProperty());
    createDate.valueProperty().bindBidirectional(viewModel.getCreateDateProperty());
    returnDate.valueProperty().bindBidirectional(viewModel.getReturnDateProperty());
    totalItemsInBasket.textProperty().bind(viewModel.getTotalItemsInBasketProperty());


    tableView.setItems(viewModel.getProductsInBaskets());
    viewModel.showAllProductsInBasket();

    setUpDatePickers();
  }

  public void showAllProductsInBasket() {
      viewModel.showAllProductsInBasket();
  }

  private void setUpDatePickers() {
    createDate.setDayCellFactory(picker -> new DateCell() {
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();

        setDisable(empty || date.compareTo(today) < 0 );
      }
    });
    createDate.setValue(LocalDate.now());

    returnDate.setDayCellFactory(picker -> new DateCell() {
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);

        setDisable(empty || date.compareTo(createDate.getValue().plusDays(1)) < 0 );
      }
    });
    returnDate.setValue(LocalDate.now().plusDays(1));

    createDate.valueProperty().addListener((ov, oldValue, newValue) -> {
      returnDate.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
          super.updateItem(date, empty);

          setDisable(empty || date.compareTo(createDate.getValue().plusDays(1)) < 0 );
        }
      });
      returnDate.setValue(createDate.getValue().plusDays(1));
    });
  }
  public void onLogOff(ActionEvent event)
  {
    viewModel.logOff();
    viewHandler.openLoginView();
  }
  public void onGoToBasketButton(ActionEvent event)
  {
    viewHandler.openCustomerBasket();
  }

  public void onGoToReservations(ActionEvent event)
  {
    viewHandler.openCustomerAllOrdersView();
  }
}
