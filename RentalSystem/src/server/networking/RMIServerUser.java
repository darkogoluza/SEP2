package server.networking;

import server.model.user.ManageUser;
import shared.networking.ServerUser;
import shared.objects.user.User;
import shared.util.Utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerUser implements ServerUser {

    private ManageUser model;

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
	 * @return
	 */
	@Override
	public boolean login(String username, String password) {
		return model.login(username, password);
	}

	@Override
	public User getLoggedUser() {
		return model.getLoggedUser();
	}

	@Override
	public void logout() {
		model.logout();
	}

}
