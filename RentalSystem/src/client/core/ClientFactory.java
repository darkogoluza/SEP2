package client.core;

import client.networking.ClientProxy;
import client.networking.ClientProxyManager;

/**
 * ClientFactory
 */
public class ClientFactory {
    private ClientProxy clientProxy;

    /**
     * @return Returns ClientPoxy that contains all other clients.
     */
    public ClientProxy getClient() {
        if(clientProxy == null) {
            clientProxy = new ClientProxyManager();
        }

        return clientProxy;
    }
}
