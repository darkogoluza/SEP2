package shared.objects.basket;

import shared.objects.product.Product;
import shared.objects.product.ProductList;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

/**
 * Contains all product's customer username and all the product's customer wants to order.
 */
public class Basket {
    private ProductList products;
    private String customerUsername;

    /**
     * Constructor initializing ProductList and assigning customer username.
     * @param customerUsername Customer username.
     */
    public Basket(String customerUsername) {
        this.customerUsername = customerUsername;
        products = new ProductList();
    }

    /**
     * @return Returns all ProductList with all products.
     */
    public ProductList getProducts() {
        return products;
    }

    /**
     * @return Returns customer username.
     */
    public String getCustomerUsername() {
        return customerUsername;
    }

    /**
     * Returns Dictionary with each Product in basket containing quantity.
     * @return Dictionary with Product as key and Integer a value.
     */
    public Map<Product, Integer> getAllProductsByQuantity() {
        Map<Product, Integer> map = new Hashtable<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.getByIndex(i);
            map.merge(product, 1, Integer::sum);
        }

        return map;
    }

    /**
     * @return Returns total price of all products in basket.
     */
    public double getTotalPrice() {
        double sum = 0;

        for (int i = 0; i < products.size(); i++) {
            sum += products.getByIndex(i).getPrice();
        }

        return sum;
    }

    /**
     * Removes all products from basket.
     */
    public void clear() {
        products = new ProductList();
    }
}
