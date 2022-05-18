package client.model.user;

import shared.objects.user.User;

import java.rmi.RemoteException;

public interface ManageUser
{
	void add(User user);
	User get(String username);
	void login(String username, String password);
	User getUser();

}
