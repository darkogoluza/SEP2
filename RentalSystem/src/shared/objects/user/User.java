package shared.objects.user;

import java.io.Serializable;

public class User implements Serializable {
	private String username;
	private String password;
	private UserRole role;
	private String phoneNo;

	/**
	 * Constructor for user that takes phone as parameter, and we can set his role as customer
	 * @param username Username for new User.
	 * @param password Password for new User.
	 * @param phoneNo Phone number for new User.
	 */
	public User(String username, String password, String phoneNo) {
		this.username = username;
		this.password = password;
		this.phoneNo = phoneNo;

		role = UserRole.customer;
	}

	/**
	 * Constructor
	 * @param username Username for new User.
	 * @param password Password for new User.
	 * @param role Role for new user that can be ("admin", "employee", "customer").
	 */
	public User(String username, String password, UserRole role) {
		this.username = username;
		this.password = password;
		this.phoneNo = "";
		this.role = role;
	}

	/**
	 * Constructor
	 * @param username Username for new User.
	 * @param password Password for new USer.
	 * @param phoneNo Phone number for new User.
	 * @param role Role for new user that can be ("admin", "employee", "customer").
	 */
	public User(String username, String password, String phoneNo, UserRole role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.phoneNo = phoneNo;
	}

	/**
	 * Getter for password.
	 * @return Returns password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter for phone number.
	 * @return Returns phone number.
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Getter for username.
	 * @return Returns username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Getter for Role.
	 * @return Returns the role which can be ("admin", "employee", "customer").
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * Setter for Role.
	 * @param role New Role that will be assigned to the User.
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * Converts User to string.
	 * @return
	 */
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				", phoneNo='" + phoneNo + '\'' +
				'}';
	}
}
