package client.model.reservation;

import shared.objects.product.ProductList;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.util.PropertyChangeSubject;

public interface ManageReservations extends PropertyChangeSubject
{
    void add(Reservation reservation);
    void remove(int index);
    Reservation getReservation(int index);
    ReservationList getAllReservations();
    void changeReservation(int index, ReservationStatus newStatus);
    void showAllReservations();
}
