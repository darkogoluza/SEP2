package client.model.user;

import shared.objects.user.User;
import shared.util.PropertyChangeSubject;

public interface ManageUser extends PropertyChangeSubject
{
	void add(User user);
	User get(String username);
	boolean login(String username, String password);
	User getLoggedUser();
	void logout();

}
