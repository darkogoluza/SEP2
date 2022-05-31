package client.model.reservation;

import client.networking.ClientProxy;
import shared.objects.errors.AlertHandler;
import shared.networking.model.ManageReservations;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.objects.user.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageReservationManager implements ManageReservations
{
    private PropertyChangeSupport changeSupport;
    private ClientProxy clientProxy;

    public ManageReservationManager(ClientProxy clientProxy)
    {
        changeSupport = new PropertyChangeSupport(this);
        this.clientProxy = clientProxy;
    }

    /**
     * Adding a single Reservation to database.
     * @param reservation
     */
    @Override
    public void add(Reservation reservation) {
        clientProxy.getClientReservation().add(reservation);
        changeSupport.firePropertyChange("reservationModified", null, clientProxy.getClientReservation().convertToStringArrayList());
    }

    /**
     * Removing a single Reservation by index.
     * @param index
     */
    @Override
    public void remove(int index) {
        clientProxy.getClientReservation().remove(index);
        changeSupport.firePropertyChange("reservationModified", null, clientProxy.getClientReservation().convertToStringArrayList());
    }

    /**
     * Returns a formatted sum off all products prices in a single reservation that matches a given ID.
     * @param id
     * @return
     */
    @Override
    public String getTotalPrice(int id) {
        return String.format("%.02f€", clientProxy.getClientReservation().get(id).getProducts().getTotalPrice());
    }

    /**
     * Returns a single Reservation by index.
     * @param index
     * @return
     */
    @Override public Reservation getReservationByIndex(int index)
    {
        return clientProxy.getClientReservation().getByIndex(index);
    }

    /**
     * Returns a single Reservation by id.
     * @param id
     * @return
     */
    @Override
	public Reservation getReservationById(int id)
    {
        return clientProxy.getClientReservation().get(id);
    }

    /**
     * Returns a single Reservation by username.
     * @param username
     * @return
     */
    @Override public ReservationList getReservationByUsername(String username)
    {
        return clientProxy.getClientReservation().getByUsername(username);
    }

    /**
     * Returns a list of Reservations from database.
     * @return
     */
    @Override public ReservationList getAllReservations()
    {
        return clientProxy.getClientReservation().getAll();
    }

    /**
     * Assigning new status to a single Reservation by index.
     * @param index
     * @param newStatus
     */
    @Override
    public void changeReservation(int index, ReservationStatus newStatus) {
        clientProxy.getClientReservation().changeReservation(index, newStatus);
        changeSupport.firePropertyChange("reservationModified", null, clientProxy.getClientReservation().convertToStringArrayList());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(name, listener);
    }
}
