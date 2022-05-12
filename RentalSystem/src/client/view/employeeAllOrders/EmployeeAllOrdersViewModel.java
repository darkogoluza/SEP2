package client.view.employeeAllOrders;

import client.model.ModelProxy;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class EmployeeAllOrdersViewModel
{
  private ListProperty<String> listOfOrders;
  private ModelProxy modelProxy;

  public EmployeeAllOrdersViewModel(ModelProxy modelProxy)
  {
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
	  return null;
	}
}
