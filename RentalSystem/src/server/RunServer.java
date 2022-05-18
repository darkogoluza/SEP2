package server;

import server.model.product.ManageProducts;
import server.model.product.ManageProductsManager;
import server.model.reservation.ManageReservationManager;
import server.model.reservation.ManageReservations;
import server.networking.RMIServerProduct;
import server.networking.RMIServerReservation;
import shared.util.Utils;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ManageReservations manageReservationManager = new ManageReservationManager();
        ManageProducts manageProductsManager = new ManageProductsManager();
        RMIServerReservation serverReservation = new RMIServerReservation(manageReservationManager);
        RMIServerProduct serverProduct = new RMIServerProduct(manageProductsManager);
        Registry registry = LocateRegistry.createRegistry(Utils.SERVER_PORT);
        registry.bind(Utils.SERVER_RESERVATION ,serverReservation);
        registry.bind(Utils.SERVER_PRODUCT ,serverProduct);
        System.out.println("Server started");
    }
}
