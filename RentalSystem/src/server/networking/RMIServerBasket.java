package server.networking;

import server.model.basket.ManageBasket;
import shared.networking.ServerBasket;
import shared.objects.product.Product;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

public class RMIServerBasket implements ServerBasket
{
  private ManageBasket model;

  public RMIServerBasket(ManageBasket model) throws RemoteException
  {
    this.model = model;
    UnicastRemoteObject.exportObject(this, 0);
  }
  @Override public void add(Product product)
  {
    model.add(product);
  }

  @Override public Product remove(Product product)
  {
    return model.remove(product);
  }

  @Override public void clear()
  {
    model.clear();
  }

  @Override public String getTotalPrice()
  {
    return model.getTotalPrice();
  }

  @Override public int size()
  {
    return model.size();
  }

  @Override public Map<Product, Integer> getAllProductsByQuantity()
  {
    return model.getAllProductsByQuantity();
  }

  @Override public void order(Timestamp createAt, Timestamp returnAt)
  {
    model.order(createAt, returnAt);
  }

  @Override public boolean isEmpty()
  {
    return model.isEmpty();
  }

}
