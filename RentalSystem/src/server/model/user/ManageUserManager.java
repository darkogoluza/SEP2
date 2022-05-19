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
		try {
			return db.load(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean login(String username, String password) {
		User userTmp = null;
		try {
			userTmp = db.load(username);
		} catch (SQLException e) {
			// user with this username doesn't exist
			e.printStackTrace();
		}

		// if we get user and password is correct we can log in user
		if (userTmp != null && userTmp.getPassword().equals(password)) {
			user = userTmp;
			return true;
		}

		return false;
	}

	@Override
	public User getUser() {
		if (user != null) {
			return user;
		}
		else {
			return null;
		}
	}

	@Override
	public void logout() {
		user = null;
		//TODO fire event so we can open another window
	}


}
