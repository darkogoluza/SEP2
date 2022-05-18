package shared.networking;

import shared.objects.user.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerUser extends Remote {
    void add(User user) throws RemoteException;
	User get(String username) throws RemoteException;
	void login(String username, String password) throws RemoteException;
	User getUser() throws RemoteException;
	void logout() throws RemoteException;
}
