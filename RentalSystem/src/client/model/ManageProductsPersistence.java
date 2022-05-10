package client.model;

import shared.objects.Product;
import shared.objects.ProductArrayList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageProductsPersistence
{
<<<<<<< HEAD
    ProductArrayList load();
    void save(ProductArrayList productArrayList) throws SQLException;
=======
    ProductArrayList load() throws SQLException;
    void save(ProductArrayList productArrayList);
>>>>>>> 541254b1047ace9e9ced81710ef913140e765c92
    void save(Product product) throws SQLException;
    void remove(Product product) throws SQLException;
    void clear();
}
