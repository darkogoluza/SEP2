package shared.objects.reservation;

import shared.objects.product.Product;
import shared.objects.product.ProductList;

import java.util.ArrayList;

public class ReservationList {
	private ArrayList<Reservation> reservations;

	/**
	 * Constructor initializing empty array list
	 */
	public ReservationList() {
		reservations = new ArrayList<>();
	}

	/**
	 * Add new reservation to list
	 * @param element
	 */
	public void add(Reservation element) {
		reservations.add(element);
	}

	/**
	 * Create new reservation object and add it to list
	 * @param username of customer who created reservation
	 * @param products all products of reservation
	 */
	public void add(String username, ProductList products) {
		reservations.add(new Reservation(getUniqueId(), username, products));
	}

	/**
	 * Change status of reservation
	 * @param index of reservation, we want to update
	 * @param status we want to set for reservation
	 */
	public void change(int index, ReservationStatus status) {
		reservations.get(index).setStatus(status);
	}

	/**
	 * Get reservation from list with id of reservation
	 * @param id of reservation
	 * @return Reservation object
	 */
	public Reservation get(int id) {
		for (int i = 0; i < reservations.size(); i++) {
			if (reservations.get(i).getId() == id)
				return reservations.get(id);
		}

		return null;
	}

	/**
	 * Get reservation with its index from list
	 * @param index of reservation in list
	 * @return Reservation object
	 */
	public Reservation getByIndex(int index) {
		return reservations.get(index);
	}

	/**
	 * Remove reservation from list with index
	 * @param index of reservation in list
	 * @return removed Reservation object
	 */
	public Reservation removeByIndex(int index) {
		return reservations.remove(index);
	}

	/**
	 * Return how many reservations are in list
	 * @return size of list
	 */
	public int size() {
		return reservations.size();
	}


	/**
	 * Generate unique id for new Reservation
	 * @return new unique id
	 */
	public int getUniqueId() {
		int maxId = -1;
		for (Reservation reservation: reservations) {
			if(reservation.getId() > maxId) {
				maxId = reservation.getId();
			}
		}
		return ++maxId;
	}

	/**
	 * Check if provided object is same as this ReservationList
	 * @param obj any object
	 * @return true if objects are same and false if objects are not same
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof ReservationList reservationList))
			return false;

		return reservationList.reservations.equals(reservations);
	}

	/**
	 * Create new copy of ReservationList
	 * @return copy of ReservationList
	 */
	public ReservationList copy() {
		ReservationList temp = new ReservationList();

		for (Reservation reservation : reservations) {
			temp.add(reservation);
		}

		return temp;
	}

	/**
	 * Convert ReservationList to String ArrayList
	 * @return String ArrayList
	 */
	public ArrayList<String> convertToStringArrayList() {
		ArrayList<String> temp = new ArrayList<>();

		for (Reservation reservation : reservations) {
			temp.add(reservation.toString());
		}

		return temp;
	}

	/**
	 * Create string from all reservations
	 * @return String
	 */
	public String toString() {
		StringBuilder values = new StringBuilder();

		for (Reservation reservation: reservations) {
			values.append(reservation.toString()).append("\n");
		}

		return values.toString();
	}
}
