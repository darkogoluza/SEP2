package shared.networking.server;

import shared.objects.product.Product;
import shared.objects.user.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Map;

public interface ServerBasket extends Remote
{
  void add (Product product)throws RemoteException;
  Product remove (Product product)throws RemoteException;
  void clear()throws RemoteException;
  String getTotalPrice()throws RemoteException;
  int size()throws RemoteException;
  Map<Product, Integer> getAllProductsByQuantity()throws RemoteException;
  void order(Timestamp createAt, Timestamp returnAt, User user)throws RemoteException;
  boolean isEmpty()throws RemoteException;
}
