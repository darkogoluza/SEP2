package client.view.employeeAllOrders;

import client.model.ModelProxy;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeEvent;

public class EmployeeAllOrdersViewModel
{
	private ListProperty<String> listOfOrders;
	private SimpleStringProperty searchInput;
	private ModelProxy modelProxy;

	public EmployeeAllOrdersViewModel(ModelProxy modelProxy)
	{
		searchInput = new SimpleStringProperty();
		listOfOrders = new SimpleListProperty<>();
		this.modelProxy = modelProxy;
		modelProxy.getManageReservations().addPropertyChangeListener("reservationModified", this::modifiedReservation);

		loadAllProducts();
	}

	private void modifiedReservation(PropertyChangeEvent propertyChangeEvent) {
		loadAllProducts();
	}

	public void loadAllProducts() {
		listOfOrders.set(
				FXCollections.observableArrayList(modelProxy.getManageReservations().getAllReservations().convertToStringArrayList()));
	}

	public ListProperty<String> getListOfReservationsProperty() {
		return listOfOrders;
	}

	public Property<String> getSearchProperty() {
		return searchInput;
	}

	public int openReservationByIndex(int index) {
		Reservation r = modelProxy.getManageReservations().getReservationByIndex(index);

		return r.getId();
	}

	public int reservationsCount() {
		return modelProxy.getManageReservations().getAllReservations().size();
	}

	public int openReservationById(int id) {
		Reservation r = modelProxy.getManageReservations().getReservationById(id);

		return r.getId();
	}
}
