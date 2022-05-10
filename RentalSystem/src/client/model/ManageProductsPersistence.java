package client.model;

import shared.objects.Product;
import shared.objects.ProductArrayList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageProductsPersistence
{
    ProductArrayList load() throws SQLException;
    void save(ProductArrayList productArrayList) throws SQLException;
    void save(Product product) throws SQLException;
    void change(Product product) throws SQLException;
    void remove(Product product) throws SQLException;
    void clear();
}
