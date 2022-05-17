package server.networking;

import client.model.ModelProxy;
import shared.networking.ServerReservation;

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
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void getById() {

    }

    @Override
    public void getByIndex() {

    }
}
