package client.model.reservation;

import shared.objects.product.Product;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;

import java.sql.SQLException;
import java.util.Map;

public interface ManageReservationPersistence
{
    ReservationList load() throws SQLException;
    void save(ReservationList reservationList) throws SQLException;
    void save(Reservation reservation, Map<Product, Integer> map) throws SQLException;
    void change(Reservation reservation) throws SQLException;
    void remove(Reservation reservation) throws SQLException;
    void clear();
}
