package client.model;

import client.model.basket.ManageBasketManager;
import client.model.product.ManageProductsManager;
import client.model.reservation.ManageReservationManager;
import client.model.user.ManageUserManager;
import client.networking.ClientProxy;
import shared.networking.model.ManageBasket;
import shared.networking.model.ManageProducts;
import shared.networking.model.ManageReservations;
import shared.networking.model.ManageUser;

public class ModelProxyManager implements ModelProxy {
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;
    private ManageBasket manageBasket;
    private ClientProxy clientProxy;
    private ManageUser manageUser;

    public ModelProxyManager(ClientProxy clientProxy) {
        this.clientProxy = clientProxy;
    }

    @Override
    public ManageProducts getManageProducts() {
        if(manageProducts == null){
            manageProducts = new ManageProductsManager(clientProxy);
        }

        return manageProducts;
    }

    @Override
    public ManageReservations getManageReservations() {
        if(manageReservations == null){
            manageReservations = new ManageReservationManager(clientProxy);
        }

        return manageReservations ;
    }

    @Override
    public ManageBasket getManageBasket() {
        if(manageBasket == null) {
            manageBasket = new ManageBasketManager(clientProxy);
        }

        return manageBasket;
    }

    @Override
    public ManageUser getManageUser() {
        if(manageUser == null) {
			manageUser = new ManageUserManager(clientProxy);
        }

        return manageUser;
    }
}
