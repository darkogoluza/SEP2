package server.networking;

import client.model.ModelProxy;
import shared.networking.ServerReservation;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerReservation implements ServerReservation {
    private ModelProxy modelProxy;

    public RMIServerReservation(ModelProxy modelProxy) {
        this.modelProxy = modelProxy;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Reservation reservation) {
        modelProxy.getManageReservations().add(reservation);
    }

    @Override
    public ReservationList getAll() {
        return modelProxy.getManageReservations().getAllReservations();
    }

    @Override
    public Reservation getByIndex(int index) {
        return modelProxy.getManageReservations().getReservationByIndex(index);
    }

    @Override
    public Reservation get(int id) {
        return modelProxy.getManageReservations().getReservationById(id);
    }

    @Override
    public void remove(int index) {
        modelProxy.getManageReservations().remove(index);
    }
}
