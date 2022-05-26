package server.model;

import shared.networking.model.ManageBasket;
import shared.networking.model.ManageProducts;
import shared.networking.model.ManageReservations;
import shared.networking.model.ManageUser;

public interface ModelProxy {
    ManageProducts getManageProducts();
    ManageReservations getManageReservations();
    ManageBasket getManageBasket();
    ManageUser getManageUser();
}
