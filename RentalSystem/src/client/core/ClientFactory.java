package client.core;

import client.networking.ClientProxy;
import client.networking.ClientProxyManager;

public class ClientFactory {
    private ClientProxy clientProxy;

    public ClientProxy getClient() {
        if(clientProxy == null) {
            clientProxy = new ClientProxyManager();
        }

        return clientProxy;
    }
}
