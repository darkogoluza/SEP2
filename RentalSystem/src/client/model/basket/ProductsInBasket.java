package client.model.basket;

import shared.objects.product.Product;

public class ProductsInBasket
{
   private Product product;
   private int quantity;


    public ProductsInBasket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getName() {
        return product.getColor().toString() + " " + product.getType().toString();
    }

    public String getPricePerUnit() {
        return String.format("%.02f€", product.getPrice());
    }

    public String getQuantity() {
        return quantity + "";
    }

    public String getSize() {
        return product.getSize().getSize();
    }

    public String getTotalPrice() {
        return String.format("%.02f€", product.getPrice() * quantity);
    }

    public Product getProduct() {
        return product;
    }
}
