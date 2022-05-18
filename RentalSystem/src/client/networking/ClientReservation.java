package client.networking;

import shared.networking.ServerReservation;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.util.Utils;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

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

    public void add(Reservation reservation) {
        server.add(reservation);
    }

    public ReservationList getAll() {
        return server.getAll();
    }

    public Reservation getByIndex(int index) {
        return server.getByIndex(index);
    }

    public ArrayList<String> convertToStringArrayList() {
        return server.getAll().convertToStringArrayList();
    }

    public Reservation get(int id) {
        return server.get(id);
    }

    public void remove(int index) {
        server.remove(index);
    }

    public void changeReservation(int index, ReservationStatus newStatus) {
    }

  public int getUniqueId()
  {
      return server.getUniqueId();
  }
}
