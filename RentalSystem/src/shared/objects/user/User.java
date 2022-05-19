package shared.objects.user;

import java.io.Serializable;

public class User implements Serializable {
	private String username;
	private String password;
	private UserRole role;
	private String phoneNo;

	public User(String username, String password, String phoneNo) {
		this.username = username;
		this.password = password;
		this.phoneNo = phoneNo;

		role = UserRole.customer;
	}

	public User(String username, String password, UserRole role) {
		this.username = username;
		this.password = password;
		this.phoneNo = "";
		this.role = role;
	}

	public User(String username, String password, String phoneNo, UserRole role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getUsername() {
		return username;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				", phoneNo='" + phoneNo + '\'' +
				'}';
	}
}
