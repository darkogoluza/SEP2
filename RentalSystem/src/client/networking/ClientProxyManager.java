package client.networking;

public class ClientProxyManager implements ClientProxy {
    private ClientProduct clientProduct;
    private ClientReservation clientReservation;
    private ClientUser clientCustomer;
    private ClientBasket clientBasket;

	/**
	 * Get client for product
	 * @return
	 */
    @Override
    public ClientProduct getClientProduct() {
        if(clientProduct == null) {
            clientProduct = new ClientProduct();
        }

        return clientProduct;
    }

	/**
	 * Get client for reservation
	 * @return
	 */
    @Override
    public ClientReservation getClientReservation() {
        if(clientReservation == null) {
            clientReservation = new ClientReservation();
        }

        return clientReservation;
    }

	/**
	 * Get client for basket
	 * @return
	 */
    @Override public ClientBasket getClientBasket()
    {
        if(clientBasket == null) {
            clientBasket = new ClientBasket();
        }

        return clientBasket;
    }

	/**
	 * Get client for user
	 * @return
	 */
    @Override
    public ClientUser getClientUser() {
        if( clientCustomer == null) {
            clientCustomer = new ClientUser();
        }

        return clientCustomer;
    }


}
