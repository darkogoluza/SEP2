package client.model.basket;

import shared.objects.product.Product;

/**
 * Contains same information as Product plus quantity.
 */
public class ProductsInBasket
{
   private Product product;
   private int quantity;

  /**
   * Constructor.
   * @param product
   * @param quantity
   */
    public ProductsInBasket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

  /**
   * Getter for name.
   * @return
   */
    public String getName() {
        return product.getColor().toString() + " " + product.getType().toString();
    }

  /**
   * Getter for price per unit.
   * @return
   */
    public String getPricePerUnit() {
        return String.format("%.02f€", product.getPrice());
    }

  /**
   * Getter for quantity.
   * @return
   */
    public String getQuantity() {
        return quantity + "";
    }

  /**
   * Getter for size.
   * @return
   */
    public String getSize() {
        return product.getSize().getSize();
    }

  /**
   * Getter for total price.
   * @return
   */
    public String getTotalPrice() {
        return String.format("%.02f€", product.getPrice() * quantity);
    }

  /**
   * Getter for Product.
   * @return
   */
    public Product getProduct() {
        return product;
    }
}
