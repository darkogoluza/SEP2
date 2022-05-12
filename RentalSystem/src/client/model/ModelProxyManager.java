package client.model;

import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;
import client.model.reservation.ManageReservationManager;
import client.model.reservation.ManageReservations;

public class ModelProxyManager implements ModelProxy {
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;

    @Override
    public ManageProducts getManageProducts() {
        if(manageProducts == null){
            manageProducts = new ManageProductsManager();
        }

        return manageProducts;
    }

    @Override
    public ManageReservations getManageReservations() {
        if(manageReservations == null){
            manageReservations = new ManageReservationManager();
        }

        return manageReservations ;
    }
}
