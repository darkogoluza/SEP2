package shared.networking;

import java.rmi.Remote;

public interface ServerReservation extends Remote {
    void add();
    void remove();
    void changeStatus();
    void getAll();
    void getById();
    void getByIndex();
}
