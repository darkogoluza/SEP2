package client.view.LoginView;

import client.model.ModelProxy;
import client.model.user.ManageUser;
import client.networking.ClientProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
	private ManageUser modelUser;
	private ModelProxy modelProxy;
  private StringProperty userNameProperty;
  private StringProperty passwordProperty;
  private ClientProxy clientProxy;


  public LoginViewModel(ModelProxy modelProxy)
  {
    this.modelProxy = modelProxy;
    this.modelUser = modelProxy.getManageUser();
    userNameProperty=new SimpleStringProperty();
    passwordProperty=new SimpleStringProperty();
  }

  public boolean checkIdentification(String userName, String password) {
    boolean isRegistered=modelUser.login(userName, password);
    return isRegistered;
  }

  public Property<String> getUserNameProperty(){
    return userNameProperty;
  }
  public Property<String> getPasswordProperty(){
    return passwordProperty;
  }
}
