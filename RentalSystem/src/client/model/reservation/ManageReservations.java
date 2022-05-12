package client.model.reservation;

import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationStatus;

public interface ManageReservations
{
    void add(Reservation reservation);
    void remove(int index);
    Reservation getReservation(int index);
    void changeReservation(int index, ReservationStatus newStatus);
    void showAllReservations();
}
