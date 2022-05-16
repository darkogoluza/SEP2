package client.view.CustomerSingleOrderView;

import client.model.ModelProxy;
import client.model.basket.ManageBasket;
import client.model.basket.ProductsInBasket;
import client.model.product.ManageProducts;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.objects.product.Product;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;

import java.util.HashMap;
import java.util.Map;

public class SingleOrderViewModel
{
  private Reservation reservation;
  private StringProperty username;
  private ListProperty<String> listOfProducts;
  private ModelProxy modelProxy;
  private StringProperty orderdate;
  private StringProperty ordertime;
  private StringProperty returndate;
  private StringProperty finalTotalPriceProperty;
  private int orderID;
  private ObservableList<ProductsInBasket> productsInBaskets;
  private ManageProducts modelProduct;
  private ManageBasket modelBasket;
  private ReservationList reservationlist;


  public SingleOrderViewModel(ModelProxy modelProxy)
  {
    username = new SimpleStringProperty();
    orderdate = new SimpleStringProperty();
    ordertime = new SimpleStringProperty();
    ordertime = new SimpleStringProperty();
    finalTotalPriceProperty = new SimpleStringProperty();
    productsInBaskets = FXCollections.observableArrayList();
    modelProduct = modelProxy.getManageProducts();
    modelBasket = modelProxy.getManageBasket();
    listOfProducts = new SimpleListProperty<>();
    this.modelProxy = modelProxy;
    reservationlist= modelProxy.getManageReservations().getAllReservations();
    finalTotalPriceProperty.set(modelBasket.getTotalPrice() + "");

    for(int i=1;i<reservationlist.size();i++ )
    {
      Reservation reservation = modelProxy.getManageReservations().getAllReservations().getByIndex(1);
      reservation.getId();
      reservation.getUserName();
    }

    username.setValue(reservation.getUserName());


  }


  public ObservableList<ProductsInBasket> getProductsInBaskets() {
    return productsInBaskets;
  }

  public void showAllProductsInBasket()
  {
    productsInBaskets.clear();
    Map<Product, Integer> map = modelBasket.getAllProductsByQuantity();
    for(Map.Entry<Product, Integer> entry : map.entrySet())
    {
      productsInBaskets.add(new ProductsInBasket(entry.getKey(), entry.getValue()));
    }
  }

  public void updateUserName(String userName)
  {
    username.setValue(userName);
  }


  public StringProperty getUsername()
  {
    return username;
  }
  public StringProperty getOrderDate(){return orderdate;}

  public StringProperty getOrdertime(){return ordertime;}
  public StringProperty getReturnDate(){return returndate;}

  public StringProperty getFinalTotalPriceProperty(){
    return finalTotalPriceProperty;
  }

}

