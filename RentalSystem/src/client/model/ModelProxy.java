package client.model;

import client.model.basket.ManageBasket;
import client.model.product.ManageProducts;
import client.model.reservation.ManageReservations;
import server.model.customer.ManageCustomer;

public interface ModelProxy {
    ManageProducts getManageProducts();
    ManageReservations getManageReservations();
    ManageBasket getManageBasket();
    ManageCustomer getManageCustomer();
}
