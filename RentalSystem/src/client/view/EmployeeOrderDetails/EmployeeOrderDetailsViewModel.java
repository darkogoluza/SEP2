package client.view.EmployeeOrderDetails;

import client.model.ModelProxy;
import client.model.reservation.ManageReservations;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import shared.objects.product.Color;
import shared.objects.product.Size;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class EmployeeOrderDetailsViewModel
{
  private StringProperty idProperty;
  private StringProperty priceProperty;
  private StringProperty userNameProperty;
  private StringProperty uniqueCodeProperty;
  private StringProperty phoneNumberProperty;
  private StringProperty statusProperty;
  private ManageReservations modelReservations;

  public EmployeeOrderDetailsViewModel(ModelProxy modelProxy)
  {

    idProperty = new SimpleStringProperty();
    priceProperty = new SimpleStringProperty();
    userNameProperty = new SimpleStringProperty();
    uniqueCodeProperty = new SimpleStringProperty();
    phoneNumberProperty = new SimpleStringProperty();
    statusProperty = new SimpleStringProperty();
    this.modelReservations = modelProxy.getManageReservations();
  }

  public void showId(){
    idProperty.set(""+modelReservations.getReservation(0).getId());
  }

  public void showUserName(){
    userNameProperty.set(modelReservations.getReservation(0).getUserName());
  }

  public void showCreatedAt(){
    userNameProperty.set(""+modelReservations.getReservation(0).getCreatedAt());
  }

  public void showStatus(){
    userNameProperty.set(""+modelReservations.getReservation(0).getStatus());
  }

  public void editId(int index, double price, Color color, Size size) {
    model.changeProduct(index, price, color, size);
  }



}

