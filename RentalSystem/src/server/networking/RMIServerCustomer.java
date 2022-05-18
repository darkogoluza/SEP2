package server.networking;

import server.model.customer.ManageCustomer;
import shared.networking.ServerCustomer;
import shared.objects.customer.Customer;
import shared.util.Utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerCustomer implements ServerCustomer {

    private ManageCustomer model;

    public RMIServerCustomer(ManageCustomer model) {
        this.model = model;
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void add(Customer customer)
    {
        model.add(customer);
    }
}
