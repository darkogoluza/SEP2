package client.view.EmployeeOrderDetails;

import client.model.ModelProxy;
import client.model.reservation.ManageReservations;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class EmployeeOrderDetailsViewModel
{
  private StringProperty nameOfOrderProperty;
  private StringProperty priceProperty;
  private StringProperty userNameProperty;
  private StringProperty uniqueCodeProperty;
  private StringProperty phoneNumberProperty;
  private StringProperty statusProperty;
  private ManageReservations modelReservations;

  public EmployeeOrderDetailsViewModel(ModelProxy modelProxy)
  {

    nameOfOrderProperty = new SimpleStringProperty();
    priceProperty = new SimpleStringProperty();
    userNameProperty = new SimpleStringProperty();
    uniqueCodeProperty = new SimpleStringProperty();
    phoneNumberProperty = new SimpleStringProperty();
    statusProperty = new SimpleStringProperty();
    this.modelReservations = modelProxy.getManageReservations();
    nameOfOrderProperty.set(modelReservations.getReservation(0).getUserName());
  }


}

