package client.view.LoginView;

import client.model.ModelProxy;
import client.model.reservation.ManageReservations;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.model.customer.ManageCustomer;
import shared.objects.reservation.ReservationStatus;

public class LoginViewModel
{
  private ModelProxy modelProxy;
  private ManageCustomer modelCustomer;
  private StringProperty userNameProperty;
  private StringProperty passwordProperty;


  public LoginViewModel(ModelProxy modelProxy)
  {
    this.modelProxy = modelProxy;
    this.modelCustomer = modelProxy.getManageCustomer();
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
