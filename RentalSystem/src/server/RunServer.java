package server;

import server.model.ModelProxy;
import server.model.ModelProxyManager;
import server.model.basket.ManageBasket;
import server.model.basket.ManageBasketManager;
import server.model.product.ManageProducts;
import server.model.product.ManageProductsManager;
import server.model.reservation.ManageReservationManager;
import server.model.reservation.ManageReservations;
import server.networking.RMIServerBasket;
import server.networking.RMIServerProduct;
import server.networking.RMIServerReservation;
import server.networking.ServerProxy;
import shared.networking.Server;
import shared.util.Utils;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
//        ManageReservations manageReservationManager = new ManageReservationManager();
//        ManageProducts manageProductsManager = new ManageProductsManager();
//        ManageBasket manageBasket = new ManageBasketManager();
//        RMIServerReservation serverReservation = new RMIServerReservation(manageReservationManager);
//        RMIServerProduct serverProduct = new RMIServerProduct(manageProductsManager);
//        RMIServerBasket serverBasket = new RMIServerBasket(manageBasket);
//        Registry registry = LocateRegistry.createRegistry(Utils.SERVER_PORT);
//        registry.bind(Utils.SERVER_RESERVATION ,serverReservation);
//        registry.bind(Utils.SERVER_PRODUCT ,serverProduct);
//        registry.bind(Utils.SERVER_BASKET,serverBasket);

        ModelProxy model = new ModelProxyManager();
        Server server = new ServerProxy(model);

        Registry registry = LocateRegistry.createRegistry(Utils.SERVER_PORT);
        registry.bind(Utils.SERVER_RENTAL, server);

        System.out.println("Server started");
    }
}
