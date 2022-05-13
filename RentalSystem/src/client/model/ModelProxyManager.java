package client.model;

import client.model.basket.ManageBasket;
import client.model.basket.ManageBasketManager;
import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;
import client.model.reservation.ManageReservationManager;
import client.model.reservation.ManageReservations;

public class ModelProxyManager implements ModelProxy {
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;
    private ManageBasket manageBasket;

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

    @Override
    public ManageBasket getManageBasket() {
        if(manageBasket == null) {
            manageBasket = new ManageBasketManager();
        }

        return manageBasket;
    }
}
