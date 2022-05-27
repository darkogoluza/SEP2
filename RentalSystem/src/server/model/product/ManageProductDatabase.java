package server.model.product;

import javafx.scene.image.Image;
import shared.objects.errors.AlertHandler;
import shared.objects.product.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

/**
 * Class deals with communication with SQL database.
 */
public class ManageProductDatabase implements ManageProductsPersistence
{
    public ManageProductDatabase() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    /**
     * Establishes a connection with database.
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=rentalsystem";
    String user = "postgres";
    String pw = "admin";
    Connection connection = null;
        connection = DriverManager.getConnection(url, user, pw);
    return connection;
}

    /**
     * Loads all Products from SQL database and returns them as a list.
     * @return
     * @throws SQLException
     */
    @Override
    public ProductList load() throws SQLException {
        ProductList list = new ProductList();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
              int id = resultSet.getInt("id");
                EquipmentType type = EquipmentType.valueOf(resultSet.getString("name"));
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
                int amount = resultSet.getInt("amount");

                Product product = new Product(id, price, color, type, size, amount);
                list.add(product);
            }
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Clears Product table from database and inserts new Products from list.
     * @param productList
     * @throws SQLException
     */
    @Override
    public void save(ProductList productList) throws SQLException {
        Connection connection = getConnection();
        for (int i = 0; i< productList.size(); i++)
        {
            Product product= productList.get(i);
            try
            {
                PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Product(id, name, size, color, price, amount) VALUES(?, ?, ?, ?, ?, ?);");
                executeStatement(statement, product);
            }
            finally {
                connection.close();
            }
        }

    }

    /**
     * Saves a single Product to database.
     * @param product
     * @throws SQLException
     */
    @Override
    public void save(Product product, String path) throws SQLException {
        Connection connection = getConnection();
        try
        {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Product(id, name, size, color, price, amount, amount_rented, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            executeStatement(statement, product, path);
        }
        finally {
            connection.close();
        }
    }

    /**
     * Changes a single Product from database.
     * @param product
     * @throws SQLException
     */
    @Override
    public void change(Product product) throws SQLException {
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement =
					connection.prepareStatement("UPDATE Product SET name = ?, size = ?, color = ?, price = ?, amount = ? WHERE id = ?");
            statement.setString(1, product.getType().toString());
            statement.setString(2, product.getSize().toString());
            statement.setString(3, product.getColor().toString());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getAmount());
            statement.setInt(6, product.getId());

            statement.executeUpdate();
		}
		finally {
			connection.close();
		}
    }


    /**
     * Removes a single Product from database.
     * @param product
     * @throws SQLException
     */
    @Override
    public void remove(Product product) throws SQLException {
        Connection connection = getConnection();
        try
        {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM reservation_product WHERE productid = ?");
            statement.setInt(1, product.getId());
            statement.executeUpdate();

			statement = connection.prepareStatement("DELETE FROM Product WHERE id = ?");
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
        finally {
            connection.close();
        }
    }

    /**
     * Returns a rented quantity of a single Product that matches a given ID.
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int getRentedAmount(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("""
                SELECT amount_rented
                FROM Product
                WHERE id = ?;""");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("amount_rented");
        }

        return 0;
    }

    public byte[] getImage(int id) {
		byte[] byteImg = null;

		Connection connection = null;
		try {
			connection = getConnection();

			PreparedStatement ps =
					connection.prepareStatement("SELECT image FROM product WHERE id = ?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				byteImg = rs.getBytes(1);
			}
			rs.close();
			ps.close();

			return byteImg;
		} catch (Exception e) {
			AlertHandler.getInstance().wrongFile();
			return null;
		}
	}

	private void executeStatement(PreparedStatement statement, Product product, String path) throws SQLException {
		statement.setInt(1, product.getId());
		statement.setString(2, product.getType().toString());
		statement.setString(3, product.getSize().toString());
		statement.setString(4, product.getColor().toString());
		statement.setDouble(5, product.getPrice());
		statement.setInt(6, product.getAmount());
		statement.setInt(7, 0);
		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			statement.setBinaryStream(8, fis, file.length());
			statement.executeUpdate();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void executeStatement(PreparedStatement statement, Product product) throws SQLException {
		statement.setInt(1, product.getId());
		statement.setString(2, product.getType().toString());
		statement.setString(3, product.getSize().toString());
		statement.setString(4, product.getColor().toString());
		statement.setDouble(5, product.getPrice());
		statement.setInt(6, product.getAmount());
		statement.setInt(7, 0);

		statement.executeUpdate();

	}

}
