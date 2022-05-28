package server.networking;

import shared.networking.model.ManageBasket;
import shared.networking.server.ServerBasket;
import shared.objects.product.Product;
import shared.objects.user.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Class manly manages Products in basket.
 */
public class RMIServerBasket implements ServerBasket
{
  private ManageBasket model;

  /**
   * @param model Contains all the functionality.
   * @throws RemoteException Object was not exported correctly
   */
  public RMIServerBasket(ManageBasket model) throws RemoteException
  {
    this.model = model;
    UnicastRemoteObject.exportObject(this, 0);
  }

  /**
   * Adding Product to Basket.
   * @param product
   */
  @Override public void add(Product product)
  {
    model.add(product);
  }

  /**
   * Remove a single Product from basket by matching Product.
   * @param product
   * @return Returns removed Product.
   */
  @Override public Product remove(Product product)
  {
    return model.remove(product);
  }

  /**
   * Empties the Basket.
   */
  @Override public void clear()
  {
    model.clear();
  }

  /**
   * Sums up all the Product prices in Basket.
   * @return
   */
  @Override public String getTotalPrice()
  {
    return model.getTotalPrice();
  }

  /**
   * Returns total count of products in Basket.
   * @return
   */
  @Override public int size()
  {
    return model.size();
  }

  /**
   * Returns a Map that contains Product as a key and quantity as Value.
   * Value for each key just tells how many of that Product the Basket contains.
   * @return
   */
  @Override public Map<Product, Integer> getAllProductsByQuantity()
  {
    return model.getAllProductsByQuantity();
  }

  /**
   * Creates new Order with createAt and returnAt Timestamps passed from view.
   * @param createAt
   * @param returnAt
   */
  @Override public void order(Timestamp createAt, Timestamp returnAt, User user)
  {
    model.order(createAt, returnAt, user);
  }

  /**
   * Checks if the Basket is empty.
   * @return
   */
  @Override public boolean isEmpty()
  {
    return model.isEmpty();
  }

}
