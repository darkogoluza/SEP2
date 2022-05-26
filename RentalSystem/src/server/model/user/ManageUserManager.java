package server.model.user;

import shared.networking.model.ManageUser;
import shared.objects.user.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ManageUserManager implements ManageUser {
	public static User user;
	private PropertyChangeSupport changeSupport;
	private ManageUserPersistance db;

	public ManageUserManager() {
		changeSupport = new PropertyChangeSupport(this);
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
			login(user.getUsername(), user.getPassword());
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
			changeSupport.firePropertyChange("login", null, username);
			return true;
		}

		return false;
	}

	public User getLoggedUser() {
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
