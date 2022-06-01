package client.model.basket;

import shared.objects.product.Product;

public class ProductsInBasket
{
   private Product product;
   private int quantity;

	/**
	 * Initializing constructor
	 * @param product
	 * @param quantity
	 */
    public ProductsInBasket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

	/**
	 * Create name of product with joining color and type
	 * @return
	 */
    public String getName() {
        return product.getColor().toString() + " " + product.getType().toString();
    }

	/**
	 * Get price of product
	 * @return
	 */
    public String getPricePerUnit() {
        return String.format("%.02f€", product.getPrice());
    }

	/**
	 * Get amount of product in basket
	 * @return
	 */
    public String getQuantity() {
        return quantity + "";
    }

	/**
	 * Get size of product
	 * @return
	 */
    public String getSize() {
        return product.getSize().getSize();
    }

	/**
	 * Get total price
	 * @return
	 */
    public String getTotalPrice() {
        return String.format("%.02f€", product.getPrice() * quantity);
    }

	/**
	 * Get product object
	 * @return
	 */
    public Product getProduct() {
        return product;
    }
}
