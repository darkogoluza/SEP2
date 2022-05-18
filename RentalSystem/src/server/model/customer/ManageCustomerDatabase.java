package server.model.customer;

import shared.objects.customer.Customer;
import shared.objects.product.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageCustomerDatabase implements ManageCustomerPersistence{

    public ManageCustomerDatabase() throws SQLException {
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

    public void save(Customer customer) throws SQLException
    {
        Connection connection = getConnection();
        try
        {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Customer(username, phoneno, password)  VALUES(?, ?, ?);");
            executeStatement(statement, customer);
        }
        finally {
            connection.close();
        }
    }

    private void executeStatement(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getUserName());
        statement.setString(2, customer.getPhoneNo());
        statement.setString(3, customer.getPassword());

        statement.executeUpdate();
    }

}
