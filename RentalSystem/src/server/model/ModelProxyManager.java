package server.model;

import shared.networking.model.ManageBasket;
import server.model.basket.ManageBasketManager;
import shared.networking.model.ManageProducts;
import server.model.product.ManageProductsManager;
import server.model.reservation.ManageReservationManager;
import shared.networking.model.ManageReservations;
import shared.networking.model.ManageUser;
import server.model.user.ManageUserManager;

/**
 * Model proxy is a bridge to all other models implementation.
 * Example accessing ManageProducts is done by calling ModelProxy.getManageProducts(); same is done for all other models.
 */
public class ModelProxyManager implements ModelProxy
{
    private ManageProducts manageProducts;
    private ManageReservations manageReservations;
    private ManageBasket manageBasket;
    private ManageUser manageUser;

    /**
     * @return Returns ManageProducts interface. It manly deals with saving and loading of Products.
     */
    @Override
    public ManageProducts getManageProducts() {
        if(manageProducts == null){
            manageProducts = new ManageProductsManager();
        }

        return manageProducts;
    }

    /**
     * @return Returns ManageReservations interface. It manly deals with saving and loading of Reservations.
     */
    @Override
    public ManageReservations getManageReservations() {
        if(manageReservations == null){
            manageReservations = new ManageReservationManager();
        }

        return manageReservations ;
    }

    /**
     * @return Returns ManageBasket interface. It manly manages Products in basket.
     */
    @Override
    public ManageBasket getManageBasket() {
        if(manageBasket == null) {
            manageBasket = new ManageBasketManager();
        }

        return manageBasket;
    }

    /**
     * @return Returns ManageUser interface. It manly deals with users, such as logging, creating and logging off users.
     */
	@Override
	public ManageUser getManageUser() {
		if (manageUser == null) {
			manageUser = new ManageUserManager();
		}
		return manageUser;
	}
}
