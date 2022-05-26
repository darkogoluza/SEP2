package client.view.customerAllOrdersView;

import client.model.ModelProxy;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import shared.networking.model.ManageBasket;
import shared.networking.model.ManageReservations;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeEvent;

public class CustomerAllOrdersViewModel
{
  private StringProperty totalItemsInBasketProperty;
  private StringProperty userNameProperty;
  private ListProperty<String> listOfOrders;
  private SimpleStringProperty searchInput;
  private ManageReservations modelReservations;
  private ModelProxy modelProxy;
  private ManageBasket modelBasket;
  private String filterStatus;

  public CustomerAllOrdersViewModel(ModelProxy modelProxy)
  {
    searchInput = new SimpleStringProperty();
    listOfOrders = new SimpleListProperty<>();
    this.modelReservations = modelProxy.getManageReservations();
    this.modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);
    this.modelProxy = modelProxy;
    this.modelBasket = modelProxy.getManageBasket();
    userNameProperty = new SimpleStringProperty();
    totalItemsInBasketProperty = new SimpleStringProperty();
    totalItemsInBasketProperty.set("" + modelBasket.size());
    userNameProperty.set(
        modelProxy.getManageUser().getLoggedUser().getUsername());
    loadAllProducts();

    modelProxy.getManageUser().addPropertyChangeListener("login",
            (event) ->  loadAllProducts()
    );
  }

  private void modifiedReservation(PropertyChangeEvent propertyChangeEvent)
  {
    loadAllProducts();
  }

  public void loadAllProducts()
  {
    listOfOrders.set(FXCollections.observableArrayList(modelReservations.getReservationByUsername(
        modelProxy.getManageUser().getLoggedUser().getUsername()).convertToStringArrayList()));
  }

  public ListProperty<String> getListOfReservationsProperty()
  {
    return listOfOrders;
  }

  public Property<String> getSearchProperty()
  {
    return searchInput;
  }

  public int openReservationByIndex(int index)
  {
    Reservation r = modelReservations.getReservationByIndex(index);
    return r.getId();
  }

  public Reservation openReservationById(int id)
  {
    return modelReservations.getReservationById(id);
  }

  public void logOff()
  {
    modelBasket.clear();
    modelProxy.getManageUser().logout();
  }

  public StringProperty getTotalItemsInBasketProperty()
  {
    return totalItemsInBasketProperty;
  }

  public Property<String> getUserNameProperty()
  {
    return userNameProperty;
  }
}
