package client.model.reservation;

import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.objects.user.User;
import shared.util.PropertyChangeSubject;

public interface ManageReservations extends PropertyChangeSubject
{
    void add(Reservation reservation);
    void remove(int index);
    Reservation getReservationByIndex(int index);
    Reservation getReservationById(int id);
    ReservationList getAllReservations();
    void changeReservation(int index, ReservationStatus newStatus);
    String getTotalPrice(int id);
    User getUser(String username);
    ReservationList getReservationByUsername(String username);
}
