package client.networking;

import shared.networking.ServerUser;
import shared.objects.customer.Customer;
import shared.objects.customer.CustomerList;
import shared.objects.reservation.ReservationList;
import shared.util.Utils;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientCustomer {
    private ServerUser server;

    public ClientCustomer() {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
            server = (ServerUser) registry.lookup(Utils.SERVER_RESERVATION);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void add(Customer customer)
    {
        server.add(customer);
    }

    public CustomerList getAll() {
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
}
