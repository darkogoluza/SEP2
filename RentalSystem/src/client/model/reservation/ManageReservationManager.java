package client.model.reservation;

import client.networking.ClientProxy;
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

    @Override
    public void add(Reservation reservation) {
        clientProxy.getClientReservation().add(reservation);
        changeSupport.firePropertyChange("reservationModified", null, clientProxy.getClientReservation().convertToStringArrayList());
    }

    @Override
    public void remove(int index) {
        clientProxy.getClientReservation().remove(index);
        changeSupport.firePropertyChange("reservationModified", null, clientProxy.getClientReservation().convertToStringArrayList());
    }

    @Override
    public String getTotalPrice(int id) {
        return String.format("%.02f€", clientProxy.getClientReservation().get(id).getProducts().getTotalPrice());
    }

    @Override public Reservation getReservationByIndex(int index)
    {
        return clientProxy.getClientReservation().getByIndex(index);
    }

    @Override
	public Reservation getReservationById(int id)
    {
        return clientProxy.getClientReservation().get(id);
    }

    @Override public ReservationList getAllReservations()
    {
        return clientProxy.getClientReservation().getAll();
    }

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
