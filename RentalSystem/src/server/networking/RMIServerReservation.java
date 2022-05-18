package server.networking;

import client.model.ModelProxy;
import server.model.reservation.ManageReservationManager;
import server.model.reservation.ManageReservations;
import shared.networking.ServerReservation;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerReservation implements ServerReservation {
    private ManageReservations model;

    public RMIServerReservation(ManageReservations model) {
        this.model = model;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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

    @Override
    public Reservation get(int id) {
        return model.getReservationById(id);
    }

    @Override
    public void remove(int index) {
        model.remove(index);
    }

    @Override public int getUniqueId()
    {
        return model.getAllReservations().getUniqueId();
    }
}
