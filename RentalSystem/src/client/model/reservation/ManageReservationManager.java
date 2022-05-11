package client.model.reservation;

import client.model.product.ManageProductDatabase;
import shared.objects.ProductArrayList;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;

import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ManageReservationManager implements ManageReservations
{
  private ReservationList list;
  private PropertyChangeSupport changeSupport;
  private ManageReservationDatabase manageReservationDatabase;

  public ManageReservationManager()
  {
    list = new ReservationList();
    changeSupport = new PropertyChangeSupport(this);
    try {
      manageReservationDatabase = new ManageReservationDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      list = manageReservationDatabase.load();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public void add(Reservation reservation)
  {

  }

  @Override public void remove(String id)
  {

  }

  @Override public Reservation getReservation(int index)
  {
    return null;
  }

  @Override public void changeReservation(int index,
      ReservationStatus newStatus)
  {

  }

  @Override public void showAllReservations()
  {

  }
}
