package shared.networking;

import shared.objects.customer.Customer;

import java.rmi.Remote;

public interface ServerCustomer extends Remote {
    void add(Customer customer);
}
