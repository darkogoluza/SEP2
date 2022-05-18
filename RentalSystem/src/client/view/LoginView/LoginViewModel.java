package client.view.LoginView;

import client.model.ModelProxy;
import client.model.user.ManageUser;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
	private ManageUser modelUser;
	private ModelProxy modelProxy;
  private StringProperty userNameProperty;
  private StringProperty passwordProperty;


  public LoginViewModel(ModelProxy modelProxy)
  {
    this.modelProxy = modelProxy;
    this.modelUser = modelProxy.getManageUser();
    userNameProperty=new SimpleStringProperty();
    passwordProperty=new SimpleStringProperty();
  }

  public void checkUserName(String userName) {
    userNameProperty.set(userName);
  }

  public void checkPassword(String password) {
    passwordProperty.set(password);
  }

  public Property<String> getUserNameProperty(){
    return userNameProperty;
  }
}
