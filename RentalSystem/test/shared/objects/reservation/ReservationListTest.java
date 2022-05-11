package shared.objects.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class ReservationListTest {

	private ReservationList list;

	@BeforeEach
	void setUp() {
		list = new ReservationList();
	}

	@Test
	void add() {
		list.add(new Reservation(0, "name", new ProductArrayList()));
		list.add(new Reservation(1, "name", new ProductArrayList()));
		assertEquals(2, list.size());
	}

	@Test
	void testAdd() {
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());
		assertEquals(2, list.size());
	}

	@Test
	void change() {
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());
		list.change(0, ReservationStatus.returned);
		assertEquals(ReservationStatus.returned, list.getByIndex(0).getStatus());
	}

	@Test
	void get() {
		Reservation r = new Reservation(0, "name", new ProductArrayList());
		list.add(r);

		assertEquals(r, list.get(0));
	}

	@Test
	void getByIndex() {
		Reservation r = new Reservation(0, "name", new ProductArrayList());
		list.add(r);

		assertEquals(r, list.getByIndex(0));
	}

	@Test
	void removeByIndex() {
		Reservation r = new Reservation(0, "name", new ProductArrayList());
		list.add(r);

		assertEquals(r, list.removeByIndex(0));
	}

	@Test
	void size() {
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());

		assertEquals(3, list.size());
	}

	@Test
	void getUniqueId() {
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());

		assertEquals(3, list.getUniqueId());
	}

	@Test
	void testEquals() {
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());

		ReservationList list2 = new ReservationList();

		list2.add("name", new ProductArrayList());
		list2.add("name", new ProductArrayList());
		list2.add("name", new ProductArrayList());

		assertTrue(list2.equals(list));
	}

	@Test
	void copy() {
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());
		list.add("name", new ProductArrayList());

		assertTrue(list.equals(list.copy()));
	}

	@Test
	void convertToStringArrayList() {
	}

	@Test
	void testToString() {
		ProductArrayList productArrayList = new ProductArrayList();
		productArrayList.add(1, Color.red, EquipmentType.ski, new MetricFormat(2));

		list.add("name", productArrayList);

		String str = "id: 0\n" +
				"username: name\n" +
				//its hard to compare time
				"created at: Wed May 11 21:54:18 CEST 2022\n" +
				"status: rented\n" +
				"Id: 000  red  2.00cm  ski  1.00€";

		assertEquals(str, list.toString());
	}
}
