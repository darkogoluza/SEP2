package client.model.reservation;

import shared.objects.product.Product;
import shared.objects.product.ProductList;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.util.PropertyChangeSubject;

import java.util.Map;

public interface ManageReservations extends PropertyChangeSubject
{
    void add(Reservation reservation);
    void remove(int index);
    Reservation getReservationByIndex(int index);
    Reservation getReservationById(int id);
    ReservationList getAllReservations();
    void changeReservation(int index, ReservationStatus newStatus);
    String getTotalPrice(int id);
}
