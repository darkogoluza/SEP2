package shared.objects.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.objects.product.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product = null;

    @BeforeEach
    void setUp() {
        product = new Product(0, 14.99, Color.red, EquipmentType.ski, new MetricFormat( 10));
    }

    @Test
    void getPrice() {
        assertEquals(14.99, product.getPrice());
    }

    @Test
    void setPrice() {
        product.setPrice(15.99);
        assertEquals(15.99, product.getPrice());
    }

    @Test
    void getId() {
        assertEquals(0, product.getId());
    }

    @Test
    void getColor() {
        assertEquals(Color.red, product.getColor());
    }

    @Test
    void setColor() {
        product.setColor(Color.black);
        assertEquals(Color.black, product.getColor());
    }

    @Test
    void getType() {
        assertEquals(EquipmentType.ski, product.getType());
    }

    @Test
    void getSizeMetricFormat() {
        assertEquals(new MetricFormat( 10), product.getSize());
    }

    @Test
    void getSizeLabelFormat() {
        product = new Product(0, 14.99, Color.red, EquipmentType.ski, new LabelFormat("XL"));
        assertEquals(new LabelFormat("XL"), product.getSize());
    }

    @Test
    void testToString() {
        String expectedValue = String.format("Id: %03d  %s  %s  %s  %.02f€", 0, Color.red, new MetricFormat(10), EquipmentType.ski, 14.99);
        assertEquals(expectedValue, product.toString());
    }

    @Test
    void testEqualsTrue() {
        Product product1 = product.copy();
        assertEquals(product, product1);
    }

    @Test
    void testEqualsFalse() {
        Product product1 = new Product(1, 14.99, Color.red, EquipmentType.ski, new MetricFormat( 10));
        assertNotEquals(product, product1);
    }

    @Test
    void copy() {
        Product product1 = product.copy();
        assertEquals(product, product1);
    }
}
