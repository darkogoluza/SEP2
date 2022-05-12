package shared.objects.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.objects.product.MetricFormat;

import static org.junit.jupiter.api.Assertions.*;

class MetricFormatTest {
    private MetricFormat metricFormat = null;

    @BeforeEach
    void setUp() {
        metricFormat = new MetricFormat( 25);
    }

    @Test
    void getHeight() {
        assertEquals(25, metricFormat.getHeight());
    }

    @Test
    void testToString() {
        String expectedValue = String.format("%.02fcm", 25.0);
        assertEquals(expectedValue, metricFormat.toString());
    }

    @Test
    void testEqualsTrue() {
        MetricFormat metricFormat1 = metricFormat.copy();
        assertEquals(metricFormat1, metricFormat);
    }

    @Test
    void testEqualsFalse() {
        MetricFormat metricFormat1 = new MetricFormat( 26);
        assertNotEquals(metricFormat1, metricFormat);
    }

    @Test
    void copy() {
        MetricFormat metricFormat1 = metricFormat.copy();
        assertEquals(metricFormat1, metricFormat);
    }

    @Test
    void getSize() {
        String expectedValue = String.format("%.02fcm", 25.0);
        assertEquals(expectedValue, metricFormat.getSize());
    }
}