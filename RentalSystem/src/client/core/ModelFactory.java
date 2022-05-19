package client.core;

import client.model.ModelProxy;
import client.model.ModelProxyManager;
import client.networking.ClientProxy;

/**
 * ModelFactory
 */
public class ModelFactory {

    private ModelProxy model;
    private ClientFactory client;

    /**
     * Empty constructor
     */
    public ModelFactory(ClientFactory client) {
        this.client = client;
    }

    /**
     * @return Returns ModelProxy that contains all other models.
     */
    public ModelProxy getModel() {
        if(model == null) {
            model = new ModelProxyManager(client.getClient());
        }

        return model;
    }
}