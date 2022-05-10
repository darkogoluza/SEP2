package client.model;

import shared.objects.Product;
import shared.objects.ProductArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageProductDatabase implements ManageProductsPersistence
{
    public ManageProductDatabase() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pw = "";
        try {
            Connection connection = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductArrayList load() {
        return null;
    }

    @Override
    public void save(ProductArrayList productArrayList) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void remove(Product product) {

    }

    @Override
    public void clear() {

    }
}
