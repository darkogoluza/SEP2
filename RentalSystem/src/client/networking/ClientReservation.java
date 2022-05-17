package client.networking;

import shared.networking.ServerReservation;
import shared.util.Utils;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientReservation {
    private ServerReservation server;

    public ClientReservation() {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
            server = (ServerReservation) registry.lookup(Utils.SERVER_RESERVATION);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public void add() {
        server.add();
    }

    public void remove() {
        server.remove();
    }

    public void changeStatus() {
        server.changeStatus();
    }

    public void getAll() {
        server.getAll();
    }

    public void getById() {
        server.getById();
    }

    public void getByIndex() {
        server.getByIndex();
    }
}
