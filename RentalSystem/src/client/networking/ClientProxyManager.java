package client.networking;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientProxyManager implements ClientProxy {
    private ClientProduct clientProduct;
    private ClientReservation clientReservation;
    private ClientCustomer clientCustomer;
    private ClientBasket clientBasket;

    @Override
    public ClientProduct getClientProduct() {
        if(clientProduct == null) {
            clientProduct = new ClientProduct();
        }

        return clientProduct;
    }

    @Override
    public ClientReservation getClientReservation() {
        if(clientReservation == null) {
            clientReservation = new ClientReservation();
        }

        return clientReservation;
    }

    @Override public ClientBasket getClientBasket()
    {
        if(clientBasket == null) {
            clientBasket = new ClientBasket();
        }

        return clientBasket;
    }

    @Override
    public ClientCustomer getClientCustomer() {
        if( clientCustomer == null) {
            clientCustomer = new ClientCustomer();
        }

        return clientCustomer;
    }


}
