//package client.view.EmployeeOrderDetails;
//
//import client.model.ModelProxy;
//import client.model.product.ManageProducts;
//import client.model.reservation.ManageReservations;
//import javafx.beans.property.*;
//import shared.objects.product.ProductList;
//import shared.objects.reservation.Reservation;
//import shared.objects.reservation.ReservationStatus;
//
//public class EmployeeOrderDetailsViewModel
//{
//
//  private StringProperty orderIdProperty;
//  private IntegerProperty priceProperty;
//  private StringProperty userNameProperty;
//  private StringProperty statusProperty;
//  private StringProperty createdAtDateProperty;
//  private StringProperty returnedAtDateProperty;
//  private IntegerProperty quantityProperty;
//  private IntegerProperty totalOverallPriceProperty;
//
//  private StringProperty nameOfProductProperty;
//  private StringProperty sizeProperty;
//
//  private ManageReservations modelReservations;
//  private ManageProducts modelProducts;
//
//
//  private Reservation reservation;
//  private ProductList products;
//
//  public EmployeeOrderDetailsViewModel(ModelProxy modelProxy)
//  {
//    this.modelReservations = modelProxy.getManageReservations();
//    this.modelProducts = modelProxy.getManageProducts();
//
//    orderIdProperty = new SimpleStringProperty();
//    priceProperty = new SimpleIntegerProperty();
//    userNameProperty = new SimpleStringProperty();
//    createdAtDateProperty = new SimpleStringProperty();
//    nameOfProductProperty = new SimpleStringProperty();
//    sizeProperty = new SimpleStringProperty();
//    returnedAtDateProperty=new SimpleStringProperty();
//    quantityProperty=new SimpleIntegerProperty();
//    totalOverallPriceProperty=new SimpleIntegerProperty();
//    statusProperty = new SimpleStringProperty();
//    statusProperty.setValue(ReservationStatus.rented.toString());;
//    orderIdProperty.setValue("1");
//
//    modelReservations.addPropertyChangeListener("statusChanged", this:: changeStatus);
//  }
//
//  public void showCreatedAtTime(int index) {
//    //  There is no time
//  }
//
//  public void returnedAt(int index) {
//    //  ///////
//  }
//
//
//public void updateViewModelProductInfo(){
//  statusProperty.set(""+modelReservations.getReservation(index).getStatus());
//  nameOfProductProperty.set(""+modelProducts.getProduct(index).getType());
//  sizeProperty.set(""+modelProducts.getProduct(index).getSize());
//  priceProperty.set(""+modelProducts.getProduct(index).getSize());
//}
//
//public void updateViewModelReservationInfo(){
//  userNameProperty.set(modelReservations.getReservation(index).getUserName());
//  idProperty.set(""+modelReservations.getReservation(index).getId());
//  createdAtDateProperty.set(""+modelReservations.getReservation(index).getCreatedAt());
////  returnedAtDateProperty.set(""+modelReservations.getReservation(index).getCreatedAt());
////  quantityProperty.set(""+modelReservations.getReservation(index).getQuantity());
////    totalPriceProperty.set(""+modelReservations.getReservation(index).getQuantity()*
////      modelReservations.getReservation(index).getPrice());
////  totalOverallPriceProperty.set(""+totalPriceProperty*getReservationsNum());
//
//}
//
//
//  public Property<String> getOrderIdProperty() {
//    return orderIdProperty;
//  }
//
//  public Property<String> getStatusProperty() {
//    return statusProperty;
//  }
//
//  public IntegerProperty getPriceProperty() {
//    return priceProperty;
//  }
//
//  public Property<String> getUserNameProperty() {
//    return userNameProperty;
//  }
//
//  public Property<String> getCreatedAtDateProperty() {return createdAtDateProperty;}
//
//  public Property<String> getReturnedAtDateProperty() {return returnedAtDateProperty;}
//
//  public IntegerProperty getQuantityProperty() {return quantityProperty;}
//
//  public IntegerProperty gettotalOverallPriceProperty() {
//    return totalOverallPriceProperty;
//  }
//
//  public void changeStatus(int id, ReservationStatus status) {
//    modelReservations.getReservation(id).setStatus(status);
////    OR ??
//    statusProperty.set(String.valueOf(status));
//  }
//
//  public int getReservationsNum(){
//    return modelReservations.getAllReservations().size();
//  }
//
//
//}
