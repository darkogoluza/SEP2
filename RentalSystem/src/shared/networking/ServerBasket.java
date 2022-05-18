package shared.networking;

import shared.objects.product.Product;

import java.rmi.Remote;
import java.util.Map;

public interface ServerBasket extends Remote
{
  void add (Product product);
  Product remove (Product product);
  void clear();
  String getTotalPrice();
  int size();
  Map<Product, Integer> getAllProductsByQuantity();
  void order();
  boolean isEmpty();
}
