package client.view.LoginView;

import client.model.ModelProxy;
import client.model.user.ManageUser;
import client.networking.ClientProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.objects.user.UserRole;

public class LoginViewModel
{
	private ManageUser modelUser;
  private StringProperty userNameProperty;
  private StringProperty passwordProperty;


  public LoginViewModel(ModelProxy modelProxy)
  {
    this.modelUser = modelProxy.getManageUser();
    userNameProperty=new SimpleStringProperty();
    passwordProperty=new SimpleStringProperty();
  }

  public boolean checkIdentification(String userName, String password) {
    return modelUser.login(userName, password);
  }

  public Property<String> getUserNameProperty(){
    return userNameProperty;
  }
  public Property<String> getPasswordProperty(){
    return passwordProperty;
  }

  public UserRole getRoleOfUser() {
	  return modelUser.getLoggedUser().getRole();
	}
}
