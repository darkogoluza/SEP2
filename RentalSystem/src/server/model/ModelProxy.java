package server.model;

import server.model.basket.ManageBasket;
import server.model.product.ManageProducts;
import server.model.reservation.ManageReservations;
import server.model.user.ManageUser;

public interface ModelProxy {
    ManageProducts getManageProducts();
    ManageReservations getManageReservations();
    ManageBasket getManageBasket();
    ManageUser getManageUser();
}
