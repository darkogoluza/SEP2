package client.view.EmployeeOrderDetails;

import client.model.ModelProxy;
import client.model.reservation.ManageReservations;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.objects.product.ProductList;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationStatus;

public class EmployeeOrderDetailsViewModel
{

  private StringProperty nameOfOrderProperty;
  private StringProperty priceProperty;
  private SimpleStringProperty userNameProperty;
  private SimpleStringProperty orderIdProperty;
  private StringProperty phoneNumberProperty;
  private SimpleStringProperty statusProperty;
  private ManageReservations modelReservations;

  private Reservation reservation;
  private ProductList products;

  public EmployeeOrderDetailsViewModel(ModelProxy modelProxy)
  {
	  this.modelReservations = modelProxy.getManageReservations();
//	reservation = modelReservations.getReservation(0);
//	products = reservation.getProducts();


	  statusProperty = new SimpleStringProperty();
	  statusProperty.setValue(ReservationStatus.rented.toString());
	  orderIdProperty = new SimpleStringProperty();
	  orderIdProperty.setValue("1");


//
//
//    nameOfOrderProperty = new SimpleStringProperty();
//    priceProperty = new SimpleStringProperty();
//    userNameProperty = new SimpleStringProperty();
//	userNameProperty.setValue(reservation.getUserName());
//
//	orderIdProperty.setValue(String.valueOf(reservation.getId()));
//    phoneNumberProperty = new SimpleStringProperty();
////	phoneNumberProperty.setValue(reservation);
//	statusProperty.setValue(reservation.getStatus().toString());
////    nameOfOrderProperty.set(modelReservations.getReservation(0).getUserName());

  }

	public Property<String> getOrderIdProperty() {
		return orderIdProperty;
	}

	public Property<String> getStatusProperty() {
		return statusProperty;
	}

	public void changeStatus(int id, ReservationStatus status) {
	  System.out.println(status);
  }

}

