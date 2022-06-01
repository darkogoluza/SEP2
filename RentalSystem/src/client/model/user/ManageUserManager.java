package client.model.user;

import client.networking.ClientProxy;
import shared.networking.model.ManageUser;
import shared.objects.user.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageUserManager implements ManageUser
{
	private ClientProxy clientProxy;
	private PropertyChangeSupport changeSupport;
	private User user;

	public ManageUserManager(ClientProxy clientProxy) {
		changeSupport = new PropertyChangeSupport(this);
		this.clientProxy = clientProxy;
    }

	/**
	 * Add new user to database
	 * @param user
	 */
    public void add(User user)
    {
		clientProxy.getClientUser().add(user);
    }

	/**
	 * Get user with username
	 * @param username
	 * @return
	 */
	@Override
	public User get(String username) {
		return clientProxy.getClientUser().get(username);
	}

	/**
	 * Login user with username and password
	 * @param username
	 * @param password
	 * @return
	 */
	@Override public User login(String username, String password)
	{
		user = clientProxy.getClientUser().login(username, password);
		if(user!=null) {
			changeSupport.firePropertyChange("login", null, username);
			return user;
		}
		return null;
	}

	/**
	 * Get logged user
	 * @return logged User Object
	 */
	@Override
	public User getLoggedUser() {
		return user;
	}

	/**
	 * logout user
	 */
	@Override
	public void logout() {
		clientProxy.getClientUser().logout();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(name, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(name, listener);
	}

}
