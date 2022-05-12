package shared.objects.basket;

import shared.objects.product.Product;
import shared.objects.product.ProductList;

import java.util.Dictionary;
import java.util.Hashtable;

public class Basket {
    private ProductList products;
    private String customerUsername;

    public Basket(String customerUsername) {
        this.customerUsername = customerUsername;
        products = new ProductList();
    }

    public ProductList getProducts() {
        return products;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public Dictionary<Product, Integer> getAllProductsByQuantity() {
        Dictionary<Product, Integer> map = new Hashtable<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.getByIndex(i);
            if(map.get(product) == null) {
                map.put(product, 1);
            } else {
                map.put(product, map.get(product) + 1);
            }
        }

        return map;
    }

    public double getTotalPrice() {
        double sum = 0;

        for (int i = 0; i < products.size(); i++) {
            sum += products.getByIndex(i).getPrice();
        }

        return sum;
    }

    public void clear() {
        products = new ProductList();
    }
}
