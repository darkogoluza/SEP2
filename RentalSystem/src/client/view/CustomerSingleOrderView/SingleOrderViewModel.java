package client.view.CustomerSingleOrderView;

import client.model.ModelProxy;
import client.model.basket.ProductsInBasket;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.networking.model.ManageProducts;
import shared.networking.model.ManageReservations;
import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationStatus;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Map;

public class SingleOrderViewModel
{
  private StringProperty orderIdProperty;
  private StringProperty userNameProperty;
  private StringProperty statusProperty;
  private StringProperty createdAtDateProperty;
  private StringProperty createdAtTimeProperty;
  private StringProperty returnedAtTimeProperty;
  private StringProperty returnedAtDateProperty;
  private StringProperty totalOverallPriceProperty;
  private StringProperty nameOfProductProperty;
  private StringProperty sizeProperty;
  private ManageReservations modelReservations;
  private ManageProducts modelProducts;
  private Reservation reservation;
  private int id;
  private ProductList products;
  private ObservableList<ProductsInBasket> productsInList;

  public SingleOrderViewModel(ModelProxy modelProxy, int id)
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
    totalOverallPriceProperty=new SimpleStringProperty();
    statusProperty = new SimpleStringProperty();
    statusProperty.setValue(ReservationStatus.rented.toString());;
    orderIdProperty.setValue(String.valueOf(id));
    userNameProperty.set(modelProxy.getManageUser().getLoggedUser().getUsername());
    updateViewModelReservationInfo();
  }

  private void modifiedBasket(PropertyChangeEvent propertyChangeEvent) {
    showAllProducts();
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

  public void updateViewModelReservationInfo(){
    Reservation reservation=modelReservations.getReservationById(id);

    userNameProperty.set(reservation.getUserName());
    orderIdProperty.set(""+reservation.getId());
    createdAtDateProperty.set(""+reservation.getCreatedAt());
    createdAtDateProperty.set(new SimpleDateFormat("dd MMM, yyyy").format(reservation.getCreatedAt()));
    createdAtTimeProperty.set(new SimpleDateFormat("K:mm a").format(reservation.getCreatedAt()));
    statusProperty.set(""+reservation.getStatus());
    returnedAtDateProperty.set(new SimpleDateFormat("dd MMM, yyyy").format(reservation.getExpiresAt()));
    returnedAtTimeProperty.set(new SimpleDateFormat("K:mm a").format(reservation.getExpiresAt()));
    totalOverallPriceProperty.set(modelReservations.getTotalPrice(id) + "");
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
  public StringProperty gettotalOverallPriceProperty() {
    return totalOverallPriceProperty;
  }
  public int getReservationsNum(){
    return modelReservations.getAllReservations().size();
  }
  public ObservableList<ProductsInBasket> getProductsInBaskets()
  {
    return productsInList;
  }
}
