package shared.networking;

import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;

import java.rmi.Remote;

public interface ServerReservation extends Remote {
    void add(Reservation reservation);
    ReservationList getAll();
    Reservation getByIndex(int index);
    Reservation get(int id);
    void remove(int index);
  int getUniqueId();
}
