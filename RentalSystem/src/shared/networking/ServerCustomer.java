package shared.networking;

import shared.objects.customer.Customer;
import shared.objects.customer.CustomerList;
import shared.objects.reservation.ReservationList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerCustomer extends Remote {
    void add(Customer customer);
    CustomerList getAll()throws RemoteException;

}
