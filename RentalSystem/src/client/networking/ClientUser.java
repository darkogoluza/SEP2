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

	/**
	 * Add new user to database
	 * @param user
	 */
    public void add(User user)
    {
		try {
			server.add(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Login user with username and password
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password) {
		try
		{
			return server.login(username, password);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get user with username
	 * @param username
	 * @return
	 */
	public User get(String username) {
		try {
			return server.get(username);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get logged user
	 * @return logged User Object
	 */
	public User getLoggedUser() {
		try {
			return server.getLoggedUser();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * logout user
	 * Set user object to null
	 */
	public void logout() {
		try {
			server.logout();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
