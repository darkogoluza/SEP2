package server.model.user;

import shared.objects.user.User;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

/**
 * Class manly handles logging, creating of users and logging off.
 */
public class ManageUserManager implements ManageUser{
	private User user;
	private PropertyChangeSupport changeSupport;
	private ManageUserPersistance db;

	/**
	 * Instantiates database manager.
	 */
	public ManageUserManager() {
		user = null;
		try {
			db = new ManageUserDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds new User to database.
	 * @param user
	 */
	@Override
	public void add(User user) {
		try {
			db.save(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a User from database by matching usernames.
	 * @param username
	 * @return Returns null if usernames do not match.
	 */
	@Override
	public User get(String username) {
		try {
			return db.load(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Searches for User in database by matching username and password.
	 * @param username
	 * @param password
	 * @return Returns false if logging in failed, meaning username or password were not matching.
	 */
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

	/**
	 * Returns currently logged-in User. Returns null if there is no User logged-in.
	 * @return
	 */
	@Override
	public User getLoggedUser() {
		if (user != null) {
			return user;
		}
		else {
			return null;
		}
	}

	/**
	 * Sets the user to equal null.
	 */
	@Override
	public void logout() {
		user = null;
		//TODO fire event so we can open another window
	}
}
