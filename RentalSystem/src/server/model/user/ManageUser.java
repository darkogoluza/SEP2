package server.model.user;

import shared.objects.user.User;
import shared.objects.user.UserRole;

public interface ManageUser {
	void add(User user);
	User get(String username);
	boolean login(String username, String password);
	UserRole getRole();
}
