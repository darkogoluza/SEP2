package server;

import server.model.ModelProxy;
import server.model.ModelProxyManager;
import server.model.user.ManageUser;
import server.model.user.ManageUserManager;
import server.networking.ServerProxy;
import shared.networking.Server;
import shared.objects.user.User;
import shared.util.Utils;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        ModelProxy model = new ModelProxyManager();
        Server server = new ServerProxy(model);

        Registry registry = LocateRegistry.createRegistry(Utils.SERVER_PORT);
        registry.bind(Utils.SERVER_RENTAL, server);

        System.out.println("Server started");

    }
}
