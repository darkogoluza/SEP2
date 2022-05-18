package client.networking;

import shared.networking.Server;
import shared.networking.ServerReservation;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.util.Utils;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ClientReservation implements Remote, Serializable
{
    private ServerReservation server;

    public ClientReservation() {
        try {
//            UnicastRemoteObject.exportObject(this,0);

            Registry registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
            Server serverProxy = (Server) registry.lookup(Utils.SERVER_RENTAL);

            server = serverProxy.getReservationServer();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public void add(Reservation reservation) {
        try
        {
            server.add(reservation);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public ReservationList getAll() {
        try
        {
            return server.getAll();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Reservation getByIndex(int index) {
        try
        {
            return server.getByIndex(index);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> convertToStringArrayList() {
        try
        {
            return server.getAll().convertToStringArrayList();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Reservation get(int id) {
        try
        {
            return server.get(id);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(int index) {
        try
        {
            server.remove(index);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void changeReservation(int index, ReservationStatus newStatus) {
        try {
            server.changeReservation(index, newStatus);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
