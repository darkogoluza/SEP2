package client.view.LoginView;

import client.model.ModelProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.networking.model.ManageUser;
import shared.objects.user.User;
import shared.objects.user.UserRole;

public class LoginViewModel
{
	private ManageUser modelUser;
  private StringProperty userNameProperty;
  private StringProperty passwordProperty;


	/**
	 * COnstructor
	 * @param modelProxy
	 */
  public LoginViewModel(ModelProxy modelProxy)
  {
    this.modelUser = modelProxy.getManageUser();
    userNameProperty=new SimpleStringProperty();
    passwordProperty=new SimpleStringProperty();
  }

	/**
	 * Check if user was logged
	 * @param userName
	 * @param password
	 * @return
	 */
  public User checkIdentification(String userName, String password) {
    return modelUser.login(userName, password);
  }

	/**
	 * Get username string property
	 * @return
	 */
	public Property<String> getUserNameProperty(){
    return userNameProperty;
  }

	/**
	 * Get password property
	 * @return
	 */
  public Property<String> getPasswordProperty(){
    return passwordProperty;
  }

	/**
	 * Get role of logged user
	 * @return
	 */
	public UserRole getRoleOfUser() {
	  return modelUser.getLoggedUser().getRole();
	}

	/**
	 * Clear all fields
	 */
  public void clearFields() {
    userNameProperty.set("");
    passwordProperty.set("");
  }
}
