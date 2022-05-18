package shared.networking;

import shared.objects.product.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ServerBasket extends Remote
{
  void add (Product product)throws RemoteException;
  Product remove (Product product)throws RemoteException;
  void clear()throws RemoteException;
  String getTotalPrice()throws RemoteException;
  int size()throws RemoteException;
  Map<Product, Integer> getAllProductsByQuantity()throws RemoteException;
  void order()throws RemoteException;
  boolean isEmpty()throws RemoteException;
}