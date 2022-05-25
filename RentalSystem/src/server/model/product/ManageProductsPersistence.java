package server.model.product;

import shared.objects.product.Product;
import shared.objects.product.ProductList;

import java.sql.SQLException;

public interface ManageProductsPersistence
{
    ProductList load() throws SQLException;
    void save(ProductList productList) throws SQLException;
    void save(Product product) throws SQLException;
    void change(Product product) throws SQLException;
    void remove(Product product) throws SQLException;
    int getRentedAmount(int id) throws SQLException;
}
