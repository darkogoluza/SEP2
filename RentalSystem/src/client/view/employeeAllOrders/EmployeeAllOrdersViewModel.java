package client.view.employeeAllOrders;

import client.model.ModelProxy;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import shared.objects.reservation.Reservation;

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

	public void openReservation(int index) {
	  Reservation r = modelProxy.getManageReservations().getReservation(index);
	  System.out.println(r);
	}
}
