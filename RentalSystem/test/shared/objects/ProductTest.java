package shared.objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product = null;

    @BeforeEach
    void setUp() {
        product = new Product(0, 14.99, Color.red, EquipmentType.ski, new MetricFormat(50, 10));
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
        assertEquals(new MetricFormat(50, 10), product.getSize());
    }

    @Test
    void getSizeLabelFormat() {
        product = new Product(0, 14.99, Color.red, EquipmentType.ski, new LabelFormat("XL"));
        assertEquals(new LabelFormat("XL"), product.getSize());
    }

    @Test
    void testToString() {
        String expectedValue = String.format("Id: %d\nPrice: %s€\nColor: %s\nType: %s\nSize: %s", 0, 14.99, Color.red, EquipmentType.ski, new MetricFormat(50, 10));
        assertEquals(expectedValue, product.toString());
    }

    @Test
    void testEqualsTrue() {
        Product product1 = product.copy();
        assertEquals(product, product1);
    }

    @Test
    void testEqualsFalse() {
        Product product1 = new Product(1, 14.99, Color.red, EquipmentType.ski, new MetricFormat(50, 10));
        assertNotEquals(product, product1);
    }

    @Test
    void copy() {
        Product product1 = product.copy();
        assertEquals(product, product1);
    }
}