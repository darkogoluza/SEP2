package client.model.reservation;

import client.model.product.ManageProductDatabase;
import shared.objects.Product;
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
    {	list = new ReservationList();
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

    @Override
    public void add(Reservation reservation) {
        list.add(reservation);
        changeSupport.firePropertyChange("reservationModified", null, list.convertToStringArrayList());
        try {
            manageReservationDatabase.save(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int index) {
        Reservation reservation = list.removeByIndex(index);
        changeSupport.firePropertyChange("reservationModified", null, list.convertToStringArrayList());
        try {
            manageReservationDatabase.remove(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getReservation(int index) {
        return list.getByIndex(index);
    }

    @Override
    public void changeReservation(int index, ReservationStatus newStatus) {
        Reservation reservation = list.get(index);
        reservation.setStatus(newStatus);

        try {
            manageReservationDatabase.change(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        changeSupport.firePropertyChange("reservationModified", null, list.convertToStringArrayList());
    }

    @Override
    public void showAllReservations() {

    }
}
