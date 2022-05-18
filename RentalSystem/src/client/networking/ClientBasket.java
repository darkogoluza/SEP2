package client.networking;

import shared.networking.Server;
import shared.networking.ServerBasket;
import shared.objects.product.Product;
import shared.util.Utils;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class ClientBasket implements Remote, Serializable
{
  private ServerBasket server;

  public ClientBasket(){
    try
    {
//      UnicastRemoteObject.exportObject(this,0);
      Registry registry = LocateRegistry.getRegistry(Utils.IP, Utils.SERVER_PORT);
      Server serverProxy = (Server) registry.lookup(Utils.SERVER_RENTAL);

      server = serverProxy.getBasketServer();
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }
  public void add(Product product)
  {
    try
    {
      server.add(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
  public Product remove(Product product)
  {
    try
    {
      server.remove(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return product;
  }
  public void clear()
  {
    try
    {
      server.clear();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
  public String getTotalPrice()
  {
    try
    {
      return server.getTotalPrice();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  public int Size()
  {
    try
    {
      return server.size();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return -1;
  }
  public Map<Product, Integer> getAllProductsByQuantity()
  {
    try
    {
      return server.getAllProductsByQuantity();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  public void order()
  {
    try
    {
      server.order();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
  public boolean isEmpty()
  {
    try
    {
      return server.isEmpty();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
