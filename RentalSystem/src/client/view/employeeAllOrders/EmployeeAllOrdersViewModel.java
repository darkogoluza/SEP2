package client.view.employeeAllOrders;

import client.model.ModelProxy;
import client.model.reservation.ManageReservations;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import shared.objects.reservation.Reservation;

import java.beans.PropertyChangeEvent;

public class EmployeeAllOrdersViewModel
{
	private ListProperty<String> listOfOrders;
	private SimpleStringProperty searchInput;
	private ManageReservations modelReservations;

	private String filterStatus;
	public EmployeeAllOrdersViewModel(ManageReservations manageReservations)
	{
		searchInput = new SimpleStringProperty();
		listOfOrders = new SimpleListProperty<>();
		this.modelReservations = manageReservations;
		this.modelReservations.addPropertyChangeListener("reservationModified", this::modifiedReservation);

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

	public int reservationsCount() {
		return modelReservations.getAllReservations().size();
	}

	public int openReservationById(int id) {
		Reservation r = modelReservations.getReservationById(id);
		return r.getId();
	}
	public void removeReservation(int index){
		modelReservations.remove(index);
	}

	public void changedFilterStatus(String newFilterStatus) {
		filterStatus = newFilterStatus;
		loadAllProducts();
	}
}
