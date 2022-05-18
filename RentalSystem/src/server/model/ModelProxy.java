package server.model;

import server.model.basket.ManageBasket;
import server.model.customer.ManageCustomer;
import server.model.product.ManageProducts;
import server.model.reservation.ManageReservations;

public interface ModelProxy {
    ManageProducts getManageProducts();
    ManageReservations getManageReservations();
    ManageBasket getManageBasket();
    ManageCustomer getManageCustomer();
}
