package shared.networking;

import shared.objects.customer.Customer;
import shared.objects.user.User;

import java.rmi.Remote;

public interface ServerUser extends Remote {
    void add(User user);
	User get(String username);
	void login(String username, String password);
	User getUser();
}
