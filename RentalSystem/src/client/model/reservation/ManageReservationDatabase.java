package client.model.reservation;

import shared.objects.*;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;

import java.sql.*;

public class ManageReservationDatabase implements ManageReservationPersistence{
  public ManageReservationDatabase() throws SQLException
  {
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
  public ReservationList load() throws SQLException {
    ReservationList list = new ReservationList();
    Connection connection = getConnection();
    try {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reservation");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String userName = resultSet.getString("userName"));
        String sizeString = resultSet.getString("size");
        Size size = null;



        if(sizeString.contains("cm")){
          sizeString = sizeString.substring(0, sizeString.length() - 2);
          size = new MetricFormat(Double.parseDouble(sizeString));
        } else {
          size = new LabelFormat(sizeString);
        }
        Color color = Color.valueOf(resultSet.getString("color"));
        double price = resultSet.getDouble("price");

        Reservation reservation = new Reservation(id, userName, productList);
        list.add(reservation);
      }
    } finally {
      connection.close();
    }
    return list;
  }

  @Override
  public void save(ProductArrayList productArrayList) throws SQLException {
    clear();
    Connection connection = getConnection();
    for (int i=0;i<productArrayList.size();i++)
    {
      Product product=productArrayList.get(i);
      try
      {
        PreparedStatement statement =
            connection.prepareStatement("INSERT INTO Product(id, name, size, color, price) VALUES(?, ?, ?, ?, ?);");
        executeStatement(statement, product);
      }
      finally {
        connection.close();
      }
    }

  }

  @Override
  public void save(Product product) throws SQLException {
    Connection connection = getConnection();
    try
    {
      PreparedStatement statement =
          connection.prepareStatement("INSERT INTO Product(id, name, size, color, price) VALUES(?, ?, ?, ?, ?);");
      executeStatement(statement, product);

      statement.executeUpdate();
    }
    finally {
      connection.close();
    }

  }

  @Override
  public void change(Product product) throws SQLException {
    Connection connection = getConnection();

    try
    {
      PreparedStatement statement =
          connection.prepareStatement("UPDATE Product SET id = ?, name = ?, size = ?, color = ?, price = ?");
      //@TODO maybe method for this
      executeStatement(statement, product);
    }
    finally {
      connection.close();
    }
    // TODO this tomorrow
    // add also saving products when they are edited.
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
    //TODO: make this
  }

  private void executeStatement(PreparedStatement statement, Product product) throws SQLException {
    statement.setInt(1, product.getId());
    statement.setString(2, product.getType().toString());
    statement.setString(3, product.getSize().toString());
    statement.setString(4, product.getColor().toString());
    statement.setDouble(5, product.getPrice());

    statement.executeUpdate();
  }

}
