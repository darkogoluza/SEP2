package client.view.employeeAllOrders;

import client.model.ModelProxy;
import javafx.beans.property.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import client.model.reservation.ManageReservations;
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
	public EmployeeAllOrdersViewModel(ModelProxy modelProxy)
	{
		searchInput = new SimpleStringProperty();
		listOfOrders = new SimpleListProperty<>();
		this.modelReservations = modelProxy.getManageReservations();
		this.modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);
		this.modelProxy = modelProxy;

		loadAllProducts();
	}

	private void modifiedReservation(PropertyChangeEvent propertyChangeEvent) {
		loadAllProducts();
	}

	public void loadAllProducts() {
		listOfOrders.set(
				FXCollections.observableArrayList(modelReservations.getAllReservations().filterByStatus(filterStatus).convertToStringArrayList()));
	}

	public ListProperty<String> getListOfReservationsProperty() {
		return listOfOrders;
	}

	public Property<String> getSearchProperty() {
		return searchInput;
	}

	public int openReservationByIndex(int index) {
		Reservation r = modelReservations.getReservationByIndex(index);
		return r.getId();
	}

	public Reservation openReservationById(int id) {
		return modelReservations.getReservationById(id);
	}
	public void removeReservation(int index){
		modelReservations.remove(index);
	}

    public void logOff() {
		modelProxy.getManageUser().logout();
    }

	public void changedFilterStatus(String newFilterStatus) {
		filterStatus = newFilterStatus;
		loadAllProducts();
	}
}
