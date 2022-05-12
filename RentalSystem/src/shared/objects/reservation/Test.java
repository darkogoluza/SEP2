package shared.objects.reservation;

import shared.objects.*;

public class Test {
	public static void main(String[] args) {
		ReservationList list = new ReservationList();

		ProductArrayList productArrayList = new ProductArrayList();
		productArrayList.add(1, Color.red, EquipmentType.ski, new MetricFormat(2));

		list.add("name", productArrayList);

		System.out.println(list.toString());
	}
}
