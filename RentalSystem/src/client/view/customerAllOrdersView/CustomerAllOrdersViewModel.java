package client.view.customerAllOrdersView;

import client.model.ModelProxy;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import shared.networking.model.ManageBasket;
import shared.networking.model.ManageReservations;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeEvent;

/**
 * CustomerAllOrdersViewModel for CustomerAllOrdersController
 */
public class CustomerAllOrdersViewModel
{
  private StringProperty totalItemsInBasketProperty;
  private StringProperty userNameProperty;
  private ListProperty<String> listOfOrders;
  private SimpleStringProperty searchInput;
  private ManageReservations modelReservations;
  private ModelProxy modelProxy;
  private ManageBasket modelBasket;
  private String filterStatus;

  /**
   * constructor for CustomerAllOrdersViewModel with modelProxy accessing model
   * @param modelProxy
   */
  public CustomerAllOrdersViewModel(ModelProxy modelProxy)
  {
    searchInput = new SimpleStringProperty();
    listOfOrders = new SimpleListProperty<>();
    this.modelReservations = modelProxy.getManageReservations();
    this.modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);
    this.modelProxy = modelProxy;
    this.modelBasket = modelProxy.getManageBasket();
    userNameProperty = new SimpleStringProperty();
    totalItemsInBasketProperty = new SimpleStringProperty();
    totalItemsInBasketProperty.set("" + modelBasket.size());
    userNameProperty.set(
        modelProxy.getManageUser().getLoggedUser().getUsername());
    loadAllProducts();

    modelProxy.getManageUser().addPropertyChangeListener("login",
            (event) ->{
              userNameProperty.set(
                  modelProxy.getManageUser().getLoggedUser().getUsername());
              loadAllProducts();
            }

    );
  }

  /**
   * After modifiedReservation event was called, load all product
   * @param propertyChangeEvent
   */
  private void modifiedReservation(PropertyChangeEvent propertyChangeEvent)
  {

    loadAllProducts();
  }

	/**
	 * Display all products
	 */
	public void loadAllProducts()
  {
    listOfOrders.set(FXCollections.observableArrayList(modelReservations.getReservationByUsername(
        modelProxy.getManageUser().getLoggedUser().getUsername()).convertToStringArrayList()));
  }

	/**
	 * Get list of reservations property
	 * @return
	 */
	public ListProperty<String> getListOfReservationsProperty()
  {
    return listOfOrders;
  }

	/**
	 * Get search input property
	 * @return
	 */
	public Property<String> getSearchProperty()
  {
    return searchInput;
  }

	/**
	 * OPen reservation with index
	 * @param index
	 * @return
	 */
  public int openReservationByIndex(int index)
  {
    Reservation r = modelReservations.getReservationByIndex(index);
    return r.getId();
  }

	/**
	 * Open reservation by id
	 * @param id
	 * @return
	 */
  public Reservation openReservationById(int id)
  {
    return modelReservations.getReservationById(id);
  }

	/**
	 * Logout
	 */
	public void logOff()
  {
    modelBasket.clear();
    modelProxy.getManageUser().logout();
  }

	/**
	 * Get total items in basket property
	 * @return
	 */
	public StringProperty getTotalItemsInBasketProperty()
  {
    return totalItemsInBasketProperty;
  }

	/**
	 * Get username property
	 * @return
	 */
  public Property<String> getUserNameProperty()
  {
    return userNameProperty;
  }
}
