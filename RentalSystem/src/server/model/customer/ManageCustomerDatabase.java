package server.model.customer;

import shared.objects.customer.Customer;
import shared.objects.customer.CustomerList;
import shared.objects.product.Product;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;

import java.sql.*;

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

    public CustomerList load() throws SQLException {
        CustomerList customerList = new CustomerList();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String phoneNo = resultSet.getString("phoneNo");

                Customer customer=new Customer(userName,password,phoneNo);
                customerList.add(customer);
            }
        } finally {
            connection.close();
        }

        return customerList;
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
