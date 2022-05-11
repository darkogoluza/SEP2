package client.model;

import client.model.product.ManageProducts;
import client.model.reservation.ManageReservations;

public interface ModelProxy {
    ManageProducts getManageProducts();
    ManageReservations getManageReservations();
}
