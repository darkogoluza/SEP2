package server.model.user;

import shared.objects.user.User;
import shared.objects.user.UserRole;

import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ManageUserManager implements ManageUser{
	private User user;
	private PropertyChangeSupport changeSupport;
	private ManageUserPersistance db;


	public ManageUserManager() {
		user = null;

		try {
			db = new ManageUserDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(User user) {
		try {
			db.save(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User get(String username) {
		return db.load(username);
	}

	@Override
	public boolean login(String username, String password) {
		return false;
	}

	@Override
	public UserRole getRole() {
		return null;
	}
}
