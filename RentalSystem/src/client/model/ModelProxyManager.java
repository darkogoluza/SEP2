package client.model;

import client.model.basket.ManageBasket;
import client.model.basket.ManageBasketManager;
import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;
import client.model.reservation.ManageReservationManager;
import client.model.reservation.ManageReservations;
import client.networking.ClientProxy;
import server.model.customer.ManageCustomer;
import server.model.customer.ManageCustomerManager;

public class ModelProxyManager implements ModelProxy {
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;
    private ManageBasket manageBasket;
    private ClientProxy clientProxy;
    private ManageCustomer manageCustomer;

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
    public ManageCustomer getManageCustomer() {
        if(manageCustomer == null) {
            manageCustomer = new ManageCustomerManager();
        }

        return manageCustomer;
    }
}
