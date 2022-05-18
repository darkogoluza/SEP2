package server.networking;

import server.model.ModelProxy;
import shared.networking.Server;
import shared.networking.ServerBasket;
import shared.networking.ServerProduct;
import shared.networking.ServerReservation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerProxy implements Server
{

  private ServerReservation serverReservation;
  private ServerProduct serverProduct;
  private  ServerBasket serverBasket;

  private ModelProxy model;

  public ServerProxy (ModelProxy model) {
    this.model = model;
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
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
        System.out.println("a");
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
}
