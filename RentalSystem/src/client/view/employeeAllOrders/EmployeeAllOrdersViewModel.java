package client.view.employeeAllOrders;

import client.model.ModelProxy;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import shared.networking.model.ManageReservations;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeEvent;

public class EmployeeAllOrdersViewModel
{
	private ListProperty<String> listOfOrders;
	private SimpleStringProperty searchInput;
	private ManageReservations modelReservations;
	private ModelProxy modelProxy;

	private String filterStatus;

	/**
	 * Initialization constructor
	 * @param modelProxy
	 */
	public EmployeeAllOrdersViewModel(ModelProxy modelProxy)
	{
		searchInput = new SimpleStringProperty();
		listOfOrders = new SimpleListProperty<>();
		this.modelReservations = modelProxy.getManageReservations();
		this.modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);
		this.modelProxy = modelProxy;

		loadAllReservations();
	}

	/**
	 * On modified reservation event fired
	 * @param propertyChangeEvent
	 */
	private void modifiedReservation(PropertyChangeEvent propertyChangeEvent) {
		loadAllReservations();
	}

	/**
	 * Load all reservations
	 */
	public void loadAllReservations() {
		listOfOrders.set(
				FXCollections.observableArrayList(modelReservations.getAllReservations().filterByStatus(filterStatus).convertToStringArrayList()));
	}

	/**
	 * Get list of reservations
	 * @return
	 */
	public ListProperty<String> getListOfReservationsProperty() {
		return listOfOrders;
	}

	/**
	 * Get search input property
	 * @return
	 */
	public Property<String> getSearchProperty() {
		return searchInput;
	}

	/**
	 * open reservation with index
	 * @param index
	 * @return
	 */
	public int openReservationByIndex(int index) {
		Reservation r = modelReservations.getReservationByIndex(index);
		return r.getId();
	}

	/**
	 * Open reservation with id
	 * @param id
	 * @return
	 */
	public Reservation openReservationById(int id) {
		return modelReservations.getReservationById(id);
	}

	/**
	 * Remove reservation with index
	 * @param index
	 */
	public void removeReservation(int index){
		modelReservations.remove(index);
	}

	/**
	 * Log off user
	 */
    public void logOff() {
		modelProxy.getManageUser().logout();
    }

	/**
	 * Change what reservations should be displayed depending on status
	 * @param newFilterStatus
	 */
	public void changedFilterStatus(String newFilterStatus) {
		filterStatus = newFilterStatus;
		loadAllReservations();
	}
}
