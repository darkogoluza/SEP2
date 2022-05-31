package client.networking;

import shared.networking.server.Server;
import shared.networking.server.ServerReservation;
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

/**
 * Class coumnicate with RMIServerReservation and manly deals with saving and loading of Reservations.
 */
public class ClientReservation implements Remote, Serializable
{
    private ServerReservation server;

    /**
     * Connecting to server and getting access to reservations.
     */
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

    /**
     * Add the reservation to database.
     * @param reservation Object containing all information about Reservation.
     */
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

    /**
     * Returns all the reservations from the database.
     * @return List containing all the reservations.
     */
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

    /**
     * Returns a single Reservation from database by index.
     * @param index
     * @return Object containing all information about Reservation.
     */
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

    /**
     * Returns all reservations converted to String
     * @return arraylist with reservations.
     */
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

    /**
     * Returns a single Reservation by id
     * @param id
     * @return
     */
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

    /**
     * Returns all reservations by username
     * @param username
     * @return reservation list with all user's reservations
     */
    public ReservationList getByUsername(String username)
    {
        try
         {
            return server.getByUsername(username);
         }
        catch (RemoteException e)
        {
             e.printStackTrace();
         }
        return null;
    }

    /**
     * Removes a single Reservation from database by index.
     * @param index
     */
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

    /**
     * Finds Reservation in database by index and changes all the information on it.
     * @param index
     * @param newStatus Reservation containing new information.
     * @throws RemoteException
     */
    public void changeReservation(int index, ReservationStatus newStatus) {
        try {
            server.changeReservation(index, newStatus);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
