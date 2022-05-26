package shared.networking.server;

import shared.objects.user.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerUser extends Remote {
	void add(User user) throws RemoteException;
	User get(String username) throws RemoteException;
	boolean login(String username, String password) throws RemoteException;
	User getLoggedUser() throws RemoteException;
	void logout() throws RemoteException;
}
