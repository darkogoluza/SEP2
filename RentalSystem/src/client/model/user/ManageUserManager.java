package client.model.user;

import client.networking.ClientProxy;
import shared.objects.customer.Customer;
import shared.objects.customer.CustomerList;
import shared.objects.user.User;

import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public void login(String username, String password) {
		clientProxy.getClientUser().login(username, password);
	}

	@Override
	public User get(String username) {
		return clientProxy.getClientUser().get(username);
	}

	@Override
	public User getUser() {
		return clientProxy.getClientUser().getUser();
	}

}
