package client.networking;

import shared.networking.ServerBasket;
import shared.objects.product.Product;
import shared.util.Utils;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class ClientBasket implements Remote
{
  private ServerBasket server;

  public ClientBasket(){
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
      server = (ServerBasket) registry.lookup(Utils.SERVER_BASKET);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }
  public void add(Product product)
  {
    server.add(product);
  }
  public Product remove(Product product)
  {
    server.remove(product);
    return product;
  }
  public void clear()
  {
    server.clear();
  }
  public String getTotalPrice()
  {
    return server.getTotalPrice();
  }
  public int Size()
  {
    return server.size();
  }
  public Map<Product, Integer> getAllProductsByQuantity()
  {
    return server.getAllProductsByQuantity();
  }
  public void order()
  {
    server.order();
  }
  public boolean isEmpty()
  {
    return server.isEmpty();
  }
}
