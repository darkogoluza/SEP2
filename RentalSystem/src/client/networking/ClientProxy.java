package client.networking;

public interface ClientProxy {
    ClientProduct getClientProduct();
    ClientReservation getClientReservation();
    ClientCustomer getClientCustomer();
}
