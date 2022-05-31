package client.view.LoginView;

import client.model.ModelProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.networking.model.ManageUser;
import shared.objects.user.UserRole;

/**
 LoginViewModel for LoginViewController
 */
public class LoginViewModel
{
	private ManageUser modelUser;
  private StringProperty userNameProperty;
  private StringProperty passwordProperty;

  /**
   * constructor is getting user's fields from database
   * @param modelProxy
   */
  public LoginViewModel(ModelProxy modelProxy)
  {
    this.modelUser = modelProxy.getManageUser();
    userNameProperty=new SimpleStringProperty();
    passwordProperty=new SimpleStringProperty();
  }

  /**
   * Checks if user input right name and password
   * @param userName
   * @param password
   * @return
   */
  public boolean checkIdentification(String userName, String password) {
    return modelUser.login(userName, password);
  }

  /**
   * Getter for username
   * @return
   */
  public Property<String> getUserNameProperty(){
    return userNameProperty;
  }

  /**
   * Getter for password
   * @return
   */
  public Property<String> getPasswordProperty(){
    return passwordProperty;
  }

  /**
   * Getter for user's role
   * @return
   */
  public UserRole getRoleOfUser() {
	  return modelUser.getLoggedUser().getRole();
	}

  /**
   * Clear username and password
   */
  public void clearFields() {
    userNameProperty.set("");
    passwordProperty.set("");
  }
}
