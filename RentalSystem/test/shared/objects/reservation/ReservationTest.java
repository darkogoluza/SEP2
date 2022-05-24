package shared.objects.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.objects.product.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
    private Reservation reservation = null;

    @BeforeEach
    void setUp() {
        ProductList arrayList = new ProductList();
        arrayList.add(new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        reservation = new Reservation(0, "testUser", arrayList);
    }

    @Test
    void getId() {
        assertEquals(0, reservation.getId());
    }

    @Test
    void getUserName() {
        assertEquals("testUser", reservation.getUserName());
    }

    @Test
    void getCreatedAt() {
        Date date = reservation.getCreatedAt();
        assertEquals(date, reservation.getCreatedAt());
    }

    @Test
    void getStatus() {
        assertEquals("rented", reservation.getStatus().toString());
    }

    @Test
    void getProducts() {
        Product product = new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5);
        assertEquals(product, reservation.getProducts().get(0));
    }

    @Test
    void setStatusToNotReturned() {
        reservation.setStatus(ReservationStatus.notReturned);
        assertEquals("notReturned", reservation.getStatus().toString());
    }

    @Test
    void setStatusToReturned() {
        reservation.setStatus(ReservationStatus.returned);
        assertEquals("returned", reservation.getStatus().toString());
    }

    @Test
    void testEqualsTrue() {
        ProductList arrayList = new ProductList();
        arrayList.add(new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        Reservation reservationDuplicate = new Reservation(0, "testUser", arrayList);

        assertEquals(reservationDuplicate, reservationDuplicate);
    }

    @Test
    void testEqualsFalse1() {
        ProductList arrayList = new ProductList();
        arrayList.add(new Product(1, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        Reservation reservationDuplicate = new Reservation(0, "testUser", arrayList);

        assertNotEquals(reservationDuplicate, reservation);
    }

    @Test
    void testEqualsFalse2() {
        ProductList arrayList = new ProductList();
        arrayList.add(new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        Reservation reservationDuplicate = new Reservation(1, "testUser", arrayList);

        assertNotEquals(reservationDuplicate, reservation);
    }

    @Test
    void copy() {
        Reservation copy = reservation.copy();

        assertEquals(copy, reservation);
    }

    @Test
    void testToString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy K:mm a");
        String expectedResult = String.format("<%03d> %s [%s - %s] status: %s",
                0, "testUser",
                simpleDateFormat.format(reservation.getCreatedAt()),
                simpleDateFormat.format(reservation.getExpiresAt()), "rented");

        assertEquals(expectedResult, reservation.toString());
    }

    @Test
    void expiresInTrue() {
        Timestamp currentPlusOneHour = new Timestamp(System.currentTimeMillis() + 3600000);

        reservation.setExpiresAt(currentPlusOneHour);

        assertTrue(reservation.expiresIn(1));
    }

    @Test
    void expiresInFalse() {
        Timestamp currentPlusTwoHours = new Timestamp(System.currentTimeMillis() + (3600000 * 2));

        reservation.setExpiresAt(currentPlusTwoHours);

        assertFalse(reservation.expiresIn(1));
    }
}