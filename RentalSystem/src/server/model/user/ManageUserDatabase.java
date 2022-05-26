package server.model.user;

import shared.objects.user.User;
import shared.objects.user.UserRole;
import java.sql.*;

/**
 * Class deals with communication with SQL database.
 */
public class ManageUserDatabase implements ManageUserPersistance {

	public ManageUserDatabase() throws SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
	}

	/**
	 * Establishes a connection to SQL database.
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=rentalsystem";
		String user = "postgres";
		String pw = "admin";
		Connection connection = DriverManager.getConnection(url, user, pw);
		return connection;
	}

	/**
	 * Saves a single User to database.
	 * @param user
	 * @throws SQLException
	 */
	@Override
	public void save(User user) throws SQLException {
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement =
					connection.prepareStatement("INSERT INTO RENTALUSER(username, password, phoneNo, role) VALUES(?, ?, ?, ?);");
			executeStatementUser(statement, user);
		}
		finally {
			connection.close();
		}
	}

	/**
	 * This method is used when saving Users to help reduce code in a single method.
	 * @param statement
	 * @param user
	 * @throws SQLException
	 */
	private void executeStatementUser(PreparedStatement statement, User user) throws SQLException {
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getPhoneNo());
		statement.setString(4, user.getRole().toString());

		statement.executeUpdate();
	}

	/**
	 * Loads a Single User from database with matching username.
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User load(String username) throws SQLException {
		User user = null;
		Connection connection = getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM RENTALUSER WHERE USERNAME = ?");
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String userName = resultSet.getString("username");
				String password = resultSet.getString("password");
				String phoneNo = resultSet.getString("phoneNo");
				String role = resultSet.getString("role");

				user = new User(userName, password, phoneNo, UserRole.valueOf(role));
			}
		} finally {
			connection.close();
		}

		return user;
	}
}
