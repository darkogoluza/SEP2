package client.networking;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ClientProxy {
    ClientProduct getClientProduct();
    ClientReservation getClientReservation();
    ClientBasket getClientBasket();
    ClientCustomer getClientCustomer();
}
