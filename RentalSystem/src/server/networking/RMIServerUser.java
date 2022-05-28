package server.networking;

import shared.networking.model.ManageUser;
import shared.networking.server.ServerUser;
import shared.objects.user.User;
import shared.util.Utils;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class manly deals with users, such as logging, creating and logging off users.
 */
public class RMIServerUser implements ServerUser {

    private ManageUser model;

	/**
	 * @param model Contains all the functionality.
	 */
    public RMIServerUser(ManageUser model) {
        this.model = model;
        try {
            UnicastRemoteObject.exportObject(this, Utils.SERVER_PORT);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }

    }

	/**
	 * Add account to database
	 * @param user object containing all information about user
	 */
	@Override
	public void add(User user)
    {
        model.add(user);
    }

	/**
	 * Get user from db with username
	 * @param username of user we want to get info about
	 * @return object containing all information about user
	 */
	@Override
	public User get(String username) {
		return model.get(username);
	}


	/**
	 * Login user with given credentials
	 * @param username
	 * @param password
	 * @return Returns whether the login was successful.
	 */
	@Override
	public User login(String username, String password) {
		return model.login(username, password);
	}

	/**
	 * @return Returns the user that is currently being logged in to the system.
	 */
	@Override
	public User getLoggedUser() {
		return model.getLoggedUser();
	}

	/**
	 * Removes the user from model.
	 */
	@Override
	public void logout() {
		model.logout();
	}

}
