package client.core;

import client.model.ModelProxy;
import client.model.ModelProxyManager;

/**
 * ModelFactory
 */
public class ModelFactory {

    private ModelProxy model;

    /**
     * Empty constructor
     */
    public ModelFactory() {

    }

    /**
     * @return Returns ModelProxy that contains all other models.
     */
    public ModelProxy getModel() {
        if(model == null) {
            model = new ModelProxyManager();
        }

        return model;
    }
}