package client.view.customerAllOrdersView;

import client.model.reservation.ManageReservations;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeEvent;

public class CustomerAllOrdersViewModel
{
  private ListProperty<String> listOfOrders;
  private SimpleStringProperty searchInput;
  private ManageReservations modelReservations;

  public CustomerAllOrdersViewModel(ManageReservations manageReservations)
  {
    searchInput = new SimpleStringProperty();
    listOfOrders = new SimpleListProperty<>();
    this.modelReservations = manageReservations;
    this.modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);

    loadAllProducts();
  }
  private void modifiedReservation(PropertyChangeEvent propertyChangeEvent) {
    loadAllProducts();
  }

  public void loadAllProducts() {
    listOfOrders.set(
        FXCollections.observableArrayList(modelReservations.getAllReservations().convertToStringArrayList()));
  }
  public ListProperty<String> getListOfReservationsProperty() {
    return listOfOrders;
  }

  public Property<String> getSearchProperty() {
    return searchInput;
  }

  public int openReservationByIndex(int index) {
    Reservation r = modelReservations.getReservationByIndex(index);
    return r.getId();
  }

  public int reservationsCount() {
    return modelReservations.getAllReservations().size();
  }

  public Reservation openReservationById(int id) {
    return modelReservations.getReservationById(id);
  }
}
