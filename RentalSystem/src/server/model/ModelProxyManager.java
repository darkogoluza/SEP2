package server.model;

import shared.networking.model.ManageBasket;
import server.model.basket.ManageBasketManager;
import shared.networking.model.ManageProducts;
import server.model.product.ManageProductsManager;
import server.model.reservation.ManageReservationManager;
import shared.networking.model.ManageReservations;
import shared.networking.model.ManageUser;
import server.model.user.ManageUserManager;

public class ModelProxyManager implements ModelProxy
{
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;
    private ManageBasket manageBasket;
    private ManageUser manageUser;

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
	public ManageUser getManageUser() {
		if (manageUser == null) {
			manageUser = new ManageUserManager();
		}
		return manageUser;
	}
}
