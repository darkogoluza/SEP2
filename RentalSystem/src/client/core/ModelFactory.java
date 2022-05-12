package client.core;

import client.model.ModelProxy;
import client.model.ModelProxyManager;
import client.model.product.ManageProducts;
import client.model.reservation.ManageReservations;

public class ModelFactory {

    private ModelProxy model;

    public ModelFactory() {

    }

    public ModelProxy getModel() {
        if(model == null) {
            model = new ModelProxyManager();
        }

        return model;
    }
}