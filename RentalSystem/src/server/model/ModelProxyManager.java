package server.model;

import server.model.basket.ManageBasket;
import server.model.basket.ManageBasketManager;
import server.model.customer.ManageCustomer;
import server.model.customer.ManageCustomerManager;
import server.model.product.ManageProducts;
import server.model.product.ManageProductsManager;
import server.model.reservation.ManageReservationManager;
import server.model.reservation.ManageReservations;

public class ModelProxyManager implements ModelProxy
{
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;
    private ManageBasket manageBasket;
    private ManageCustomer manageCustomer;

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

    @Override
    public ManageCustomer getManageCustomer() {
        if(manageCustomer == null) {
            manageCustomer = new ManageCustomerManager();
        }

        return manageCustomer;
    }
}
