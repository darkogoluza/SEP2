package server;

import server.model.ModelProxy;
import server.model.ModelProxyManager;
import server.model.product.ManageProductDatabase;
import server.networking.ServerProxy;
import shared.networking.server.Server;
import shared.objects.product.Color;
import shared.objects.product.EquipmentType;
import shared.objects.product.MetricFormat;
import shared.objects.product.Product;
import shared.util.Utils;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        ModelProxy model = new ModelProxyManager();
        Server server = new ServerProxy(model);

        Registry registry = LocateRegistry.createRegistry(Utils.SERVER_PORT);
        registry.bind(Utils.SERVER_RENTAL, server);

        System.out.println("Server started");

		try {
			ManageProductDatabase db = new ManageProductDatabase();
//			db.save(new Product(2, 10, Color.red, EquipmentType.ski, new MetricFormat(10)), "/Users/antondurcak/Downloads/959177506-3265590610.jpg");
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}
}
