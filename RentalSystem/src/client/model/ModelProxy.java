package client.model;

import client.model.basket.ManageBasket;
import client.model.product.ManageProducts;
import client.model.user.ManageUser;
import client.model.reservation.ManageReservations;

public interface ModelProxy {
    ManageProducts getManageProducts();
    ManageReservations getManageReservations();
    ManageBasket getManageBasket();
    ManageUser getManageUser();
}
