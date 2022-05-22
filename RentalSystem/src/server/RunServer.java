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

    }
}
