package server.networking;

import server.model.ModelProxy;
import shared.networking.*;
import shared.util.Utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerProxy implements Server
{
  private ServerReservation serverReservation;
  private ServerProduct serverProduct;
  private ServerBasket serverBasket;
  private ServerUser serverUser;

  private ModelProxy model;

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

	@Override
	public ServerUser getUserServer() {
		if (serverUser == null) {
			serverUser = new RMIServerUser(model.getManageUser());
		}

		return serverUser;
	}
}
