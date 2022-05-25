package client.model.user;

import client.networking.ClientProxy;
import shared.objects.user.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageUserManager implements ManageUser
{
	private ClientProxy clientProxy;
	private PropertyChangeSupport changeSupport;

	public ManageUserManager(ClientProxy clientProxy) {
		changeSupport = new PropertyChangeSupport(this);
		this.clientProxy = clientProxy;
    }

    public void add(User user)
    {
		clientProxy.getClientUser().add(user);
    }


	@Override
	public User get(String username) {
		return clientProxy.getClientUser().get(username);
	}

	@Override public boolean login(String username, String password)
	{
		boolean bool = clientProxy.getClientUser().login(username, password);
		if(bool) {
			changeSupport.firePropertyChange("login", null, username);
		}
		return bool;
	}

	@Override
	public User getLoggedUser() {
		return clientProxy.getClientUser().getLoggedUser();
	}

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
