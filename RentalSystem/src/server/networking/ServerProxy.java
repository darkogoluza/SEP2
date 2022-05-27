package server.networking;

import server.model.ModelProxy;
import shared.networking.server.*;
import shared.util.Utils;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server proxy is a bridge to all RMI Server implementation.
 * Example accessing RMIServerProduct is done by calling ServerProxy.getProductServer(); same is done for all other servers.
 */
public class ServerProxy implements Server
{
  private ServerReservation serverReservation;
  private ServerProduct serverProduct;
  private ServerBasket serverBasket;
  private ServerUser serverUser;

  private ModelProxy model;

  /**
   * @param model It contains every other model class, and provides getters for them.
   */
  public ServerProxy (ModelProxy model) {
    this.model = model;
    try
    {
      UnicastRemoteObject.exportObject(this, Utils.SERVER_PORT);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * @return Returns ServerProduct interface. It manly deals with saving and loading of Products.
   */
  @Override public ServerProduct getProductServer()
  {
    if (serverProduct == null) {
      try
      {
        serverProduct = new RMIServerProduct(model.getManageProducts());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

    return serverProduct;
  }

  /**
   * @return Returns ServerBasket interface. It manly manages Products in basket.
   */
  @Override public ServerBasket getBasketServer()
  {
    if (serverBasket == null) {
      try
      {
        serverBasket = new RMIServerBasket(model.getManageBasket());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

    return serverBasket;
  }

  /**
   * @return Returns ServerBasket interface. It manly deals with saving and loading of Reservations.
   */
  @Override public ServerReservation getReservationServer()
  {
    if (serverReservation == null) {
      try
      {
        serverReservation = new RMIServerReservation(model.getManageReservations());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

    return serverReservation;
  }

  /**
   * @return Returns ServerUser interface. It manly deals with users, such as logging, creating and logging off users.
   */
	@Override
	public ServerUser getUserServer() {
		if (serverUser == null) {
			serverUser = new RMIServerUser(model.getManageUser());
		}

		return serverUser;
	}
}
