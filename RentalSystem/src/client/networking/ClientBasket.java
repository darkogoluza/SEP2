package client.networking;


import shared.networking.server.Server;
import shared.networking.server.ServerBasket;
import shared.objects.product.Product;
import shared.objects.user.User;
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

public class ClientBasket implements Remote, Serializable
{
  private ServerBasket server;

	/**
	 * Client for basket which communicates with basket server
	 */
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
	 * Add product to basket and fire events
	 * @param product
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
	 * Remove product to basket and fire events
	 * @param product
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
	 * Clear basket and remove all products from basket
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
	 * Total price of basket
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
	 * Get amount of products in basket
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
	 * Get all products with quantity as hashmap
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
	 * Create order with user object, start time and return time
	 * @param createAt
	 * @param returnAt
	 * @param user
	 */
  public void order(Timestamp createAt, Timestamp returnAt, User user)
  {
    try
    {
      server.order(createAt, returnAt, user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

	/**
	 * Check if basket is empty
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
