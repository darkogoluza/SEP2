package server;

import server.model.ModelProxy;
import server.model.ModelProxyManager;
import server.model.user.ManageUser;
import server.model.user.ManageUserManager;
import server.networking.ServerProxy;
import shared.networking.Server;
import shared.objects.product.Color;
import shared.objects.product.EquipmentType;
import shared.objects.product.MetricFormat;
import shared.objects.product.Product;
import shared.objects.user.User;
import shared.util.Utils;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        ModelProxy model = new ModelProxyManager();
        Server server = new ServerProxy(model);

        Registry registry = LocateRegistry.createRegistry(Utils.SERVER_PORT);
        registry.bind(Utils.SERVER_RENTAL, server);

        System.out.println("Server started");

		Map<Product, Integer> products = new Hashtable<>();
		ArrayList<Product> list = new ArrayList<>();
		list.add(new Product(1, 10, Color.red, EquipmentType.ski, new MetricFormat(10)));
		list.add(new Product(1, 10, Color.red, EquipmentType.ski, new MetricFormat(10)));
		list.add(new Product(1, 10, Color.red, EquipmentType.ski, new MetricFormat(10)));
		list.add(new Product(1, 10, Color.red, EquipmentType.ski, new MetricFormat(10)));
		int quantity = 1;

		products.put(list.get(0), 1);

		for (int i = 1; i < list.size(); i++) {
			Product product = list.get(i);

			products.forEach((p, q) -> {
				if (p.equals(product)) {
					products.put(product, 1);
				}
				else {
					products.put(product, 1);
				}
			});

		}

		System.out.println(products);

    }
}
