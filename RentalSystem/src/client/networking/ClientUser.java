package client.networking;

import shared.networking.server.Server;
import shared.networking.server.ServerUser;
import shared.objects.user.User;
import shared.util.Utils;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientUser implements Remote, Serializable {
    private ServerUser server;

    public ClientUser() {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
			Server serverProxy = (Server) registry.lookup(Utils.SERVER_RENTAL);

			server = serverProxy.getUserServer();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void add(User user)
    {
		try {
			server.add(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public boolean login(String username, String password) {
		try
		{
			return server.login(username, password);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public User get(String username) {
		try {
			return server.get(username);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getLoggedUser() {
		try {
			return server.getLoggedUser();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void logout() {
		try {
			server.logout();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
