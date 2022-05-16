package client.view.CustomerSingleOrderView;

import client.model.ModelProxy;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import

import java.util.HashMap;

public class SingleOrderViewModel
{
  private StringProperty username;
  private ListProperty<String> listOfProducts;
  private ModelProxy modelProxy;
  private StringProperty orderdate;
  private StringProperty ordertime;

  public SingleOrderViewModel(ModelProxy modelProxy)
  {
    username = new SimpleStringProperty();
    orderdate = new SimpleStringProperty();
    ordertime = new SimpleStringProperty();
    listOfProducts = new SimpleListProperty<>();
    this.modelProxy = modelProxy;
  }

  public void updateUserName(String userName)
  {
    username.setValue(userName);
  }



  public StringProperty getUsername()
  {
    return username;
  }
  public StringProperty getOrderDate()
  {
    return reservation.getCreatedAt().toString();
  }
  public StringProperty getOrdertime(){return ordertime;}

}
