package server.networking;

import shared.networking.model.ManageReservations;
import shared.networking.server.ServerReservation;
import shared.networking.ServerReservation;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerReservation implements ServerReservation {
    private ManageReservations model;

    public RMIServerReservation(ManageReservations model) throws RemoteException
    {
        this.model = model;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void add(Reservation reservation) {
        model.add(reservation);
    }

    @Override
    public ReservationList getAll() {
        return model.getAllReservations();
    }

    @Override
    public Reservation getByIndex(int index) {
        return model.getReservationByIndex(index);
    }
    @Override public ReservationList getByUsername(String username)
    {
        return model.getReservationByUsername(username);
    }

    @Override
    public Reservation get(int id) {
        return model.getReservationById(id);
    }

    @Override
    public void remove(int index) {
        model.remove(index);
    }

    @Override
    public void changeReservation(int index, ReservationStatus newStatus) throws RemoteException {
        model.changeReservation(index, newStatus);
    }


}

