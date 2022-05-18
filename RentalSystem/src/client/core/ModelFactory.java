package client.core;

import client.model.ModelProxy;
import client.model.ModelProxyManager;
import client.networking.ClientProxy;

/**
 * ModelFactory
 */
public class ModelFactory {

    private ModelProxy model;
    private ClientProxy clientProxy;

    /**
     * Empty constructor
     */
    public ModelFactory(ClientProxy clientProxy) {
        this.clientProxy = clientProxy;
    }

    /**
     * @return Returns ModelProxy that contains all other models.
     */
    public ModelProxy getModel() {
        if(model == null) {
            model = new ModelProxyManager(clientProxy);
        }

        return model;
    }
}