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

/**
 * Class coumnicate with RMIServerUser and manly deals with users, such as logging, creating and logging off users.
 */
public class ClientUser implements Remote, Serializable {
    private ServerUser server;

	/**
	 * Client is using server to get access to all users
	 */
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
	 * Add account to database
	 * @param user object containing all information about user
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
	 * Login user with given credentials
	 * @param username
	 * @param password
	 * @return Returns whether the login was successful.
	 */
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

	/**
	 * Get user from db with username
	 * @param username of user we want to get info about
	 * @return object containing all information about user
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
	 * @return Returns the user that is currently being logged in to the system.
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
	 * Removes the user from model.
	 */
	public void logout() {
		try {
			server.logout();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
