package client.model;

import shared.objects.Product;
import shared.objects.ProductArrayList;

import java.util.ArrayList;

public interface ManageProductsPersistence
{
    ProductArrayList load();
    void save(ProductArrayList productArrayList);
    void save(Product product);
    void remove(Product product);
    public void clear();
}
