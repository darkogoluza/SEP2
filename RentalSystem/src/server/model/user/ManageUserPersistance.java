package server.model.user;

import shared.objects.user.User;

import java.sql.SQLException;

public interface ManageUserPersistance {
	void save(User user) throws SQLException;
	User load(String username) throws SQLException;
}
