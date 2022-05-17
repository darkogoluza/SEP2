package client.view.EmployeeOrderDetails;

import client.model.ModelProxy;
import client.model.basket.ProductsInBasket;
import client.model.product.ManageProducts;
import client.model.reservation.ManageReservations;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.product.Product;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationStatus;

import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Map;

public class EmployeeOrderDetailsViewModel
{

  private StringProperty orderIdProperty;
  private StringProperty userNameProperty;
  private StringProperty statusProperty;
  private StringProperty createdAtDateProperty;
  private StringProperty createdAtTimeProperty;
  private StringProperty returnedAtTimeProperty;
  private StringProperty returnedAtDateProperty;
  private IntegerProperty totalOverallPriceProperty;
  private StringProperty nameOfProductProperty;
  private StringProperty sizeProperty;
  private StringProperty finalTotalPriceProperty;
  private ManageReservations modelReservations;
  private ManageProducts modelProducts;
  private int id;
  private ObservableList<ProductsInBasket> productsInList;

  public EmployeeOrderDetailsViewModel(ModelProxy modelProxy, int id)
  {
    this.id = id;
    productsInList = FXCollections.observableArrayList();
    this.modelReservations = modelProxy.getManageReservations();
    this.modelProducts = modelProxy.getManageProducts();
    modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);

    createdAtTimeProperty=new SimpleStringProperty();
    orderIdProperty = new SimpleStringProperty();
    userNameProperty = new SimpleStringProperty();
    createdAtDateProperty = new SimpleStringProperty();
    nameOfProductProperty = new SimpleStringProperty();
    sizeProperty = new SimpleStringProperty();
    returnedAtDateProperty=new SimpleStringProperty();
    returnedAtTimeProperty = new SimpleStringProperty();
    totalOverallPriceProperty=new SimpleIntegerProperty();
    statusProperty = new SimpleStringProperty();
    statusProperty.setValue(ReservationStatus.rented.toString());;
    orderIdProperty.setValue(String.valueOf(id));
    finalTotalPriceProperty = new SimpleStringProperty();
    updateViewModelReservationInfo();

    finalTotalPriceProperty.set(modelReservations.getTotalPrice(id) + "");
  }

  private void modifiedReservation(PropertyChangeEvent propertyChangeEvent) {
   showAllProducts();
   updateViewModelReservationInfo();
  }

  public void showAllProducts()
  {
    productsInList.clear();
    Map<Product, Integer> map = modelReservations.getReservationById(id).getProducts().getAllProductsByQuantity();
    for(Map.Entry<Product, Integer> entry : map.entrySet())
    {
      productsInList.add(new ProductsInBasket(entry.getKey(), entry.getValue()));
    }
  }

  public void changeStatus(ReservationStatus status) {

    modelReservations.changeReservation(id,status);
  }

  public void updateViewModelReservationInfo(){
    Reservation reservation=modelReservations.getReservationById(id);

    userNameProperty.set(reservation.getUserName());
    orderIdProperty.set(""+reservation.getId());
    createdAtDateProperty.set(new SimpleDateFormat("dd/MM/yyyy").format(reservation.getCreatedAt()));
    createdAtTimeProperty.set(new SimpleDateFormat("HH:mm:ss").format(reservation.getCreatedAt()));
    statusProperty.set(""+reservation.getStatus());
    returnedAtDateProperty.set(new SimpleDateFormat("dd/MM/yyyy").format(reservation.getExpiresAt()));
    returnedAtTimeProperty.set(new SimpleDateFormat("HH:mm:ss").format(reservation.getExpiresAt()));
  }


  public Property<String> getOrderIdProperty() {
    return orderIdProperty;
  }
  public Property<String> getStatusProperty() {
    return statusProperty;
  }
  public Property<String> getUserNameProperty() {
    return userNameProperty;
  }
  public Property<String> getCreatedAtDateProperty() {return createdAtDateProperty;}
  public Property<String> getCreatedAtTimeProperty() {return createdAtTimeProperty;}
  public Property<String> getReturnedAtDateProperty() {return returnedAtDateProperty;}
  public Property<String> getReturnedAtTimeProperty() {return returnedAtTimeProperty;}
  public IntegerProperty gettotalOverallPriceProperty() {
    return totalOverallPriceProperty;
  }
  public StringProperty getFinalTotalPriceProperty() {
    return finalTotalPriceProperty;
  }
  public int getReservationsNum(){
    return modelReservations.getAllReservations().size();
  }
  public ObservableList<ProductsInBasket> getProductsInBaskets()
  {
    return productsInList;
  }
}
