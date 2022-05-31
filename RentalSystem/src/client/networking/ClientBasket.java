package client.networking;


import shared.networking.server.Server;
import shared.networking.server.ServerBasket;
import shared.objects.product.Product;
import shared.util.Utils;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Class coumnicates with RMIServerBasket and deals with saving and loading of Products.
 */
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

  /**
   * add product to basket
   * @param product
   * @throws RemoteException
   */
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

  /**
   * Remove product from basket
   * @param product
   * @return Product
   * @throws RemoteException
   */
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

  /**
   * Remove all products from basket
   * @throws RemoteException
   */
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

  /**
   * Sums up all the Product prices in Basket.
   * @return
   */
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

  /**
   * Returns total count of products in Basket.
   * @return
   */
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

  /**
   * Returns a Map that contains Product as a key and quantity as Value.
   * Value for each key just tells how many of that Product the Basket contains.
   * @return
   */
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

  /**
   * Creates new Order with createAt and returnAt Timestamps passed from view.
   * @param createAt
   * @param returnAt
   */
  public void order(Timestamp createAt, Timestamp returnAt)
  {
    try
    {
      server.order(createAt, returnAt);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Checks if the Basket is empty.
   * @return
   */
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
