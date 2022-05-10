package client.model;

import shared.objects.Product;
import shared.objects.ProductArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageProductDatabase implements ManageProductsPersistence
{


    public ManageProductDatabase() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    private Connection getConnection() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=rentalsystem";
    String user = "postgres";
    String pw = "admin";
    Connection connection = null;
        connection = DriverManager.getConnection(url, user, pw);
    return connection;
}

    @Override
    public ProductArrayList load() {
        return null;
    }

    @Override
    public void save(ProductArrayList productArrayList) {

    }

    @Override
    public void save(Product product) throws SQLException {
        Connection connection = getConnection();
        try
        {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Product(id, name, size, color, price) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, product.getId());
            statement.setString(2, product.getType().toString());
            statement.setString(3, product.getSize().toString());
            statement.setString(4, product.getColor().toString());
            statement.setDouble(5, product.getPrice());

            statement.executeUpdate();
        }
        finally {
            connection.close();
        }

    }

    @Override
    public void remove(Product product) throws SQLException {
        Connection connection = getConnection();
        try
        {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM Product WHERE id = ?");
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
        finally {
            connection.close();
        }
    }

    @Override
    public void clear() {

    }


}
