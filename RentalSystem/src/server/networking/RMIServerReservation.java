package server.networking;

import shared.networking.model.ManageReservations;
import shared.networking.server.ServerReservation;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class manly deals with saving and loading of Reservations.
 */
public class RMIServerReservation implements ServerReservation {
    private ManageReservations model;

    /**
     * @param model Contains all the functionality.
     * @throws RemoteException Object was not exported correctly.
     */
    public RMIServerReservation(ManageReservations model) throws RemoteException
    {
        this.model = model;
        UnicastRemoteObject.exportObject(this, 0);
    }

    /**
     * Add the reservation to database.
     * @param reservation Object containing all information about Reservation.
     */
    @Override
    public void add(Reservation reservation) {
        model.add(reservation);
    }

    /**
     * Returns all the reservations from the database.
     * @return List containing all the reservations.
     */
    @Override
    public ReservationList getAll() {
        return model.getAllReservations();
    }

    /**
     * Returns a single Reservation from database by index.
     * @param index
     * @return Object containing all information about Reservation.
     */
    @Override
    public Reservation getByIndex(int index) {
        return model.getReservationByIndex(index);
    }
    @Override public ReservationList getByUsername(String username)
    {
        return model.getReservationByUsername(username);
    }

    /**
     * Returns a single Reservation from database by matching ID.
     * @param id
     * @return Object containing all information about Reservation.
     */
    @Override
    public Reservation get(int id) {
        return model.getReservationById(id);
    }

    /**
     * Removes a single Reservation from database by index.
     * @param index
     */
    @Override
    public void remove(int index) {
        model.remove(index);
    }

    /**
     * Finds Reservation in database by index and changes all the information on it.
     * @param index
     * @param newStatus Reservation containing new information.
     * @throws RemoteException
     */
    @Override
    public void changeReservation(int index, ReservationStatus newStatus) throws RemoteException {
        model.changeReservation(index, newStatus);
    }


}

