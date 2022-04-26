package shared.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricFormatTest {
    private MetricFormat metricFormat = null;

    @BeforeEach
    void setUp() {
        metricFormat = new MetricFormat(120, 25);
    }

    @Test
    void getWidth() {
        assertEquals(120, metricFormat.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(25, metricFormat.getHeight());
    }

    @Test
    void testToString() {
        String expectedValue = String.format("%fcm x %fcm", 120.0, 25.0);
        assertEquals(expectedValue, metricFormat.toString());
    }

    @Test
    void testEqualsTrue() {
        MetricFormat metricFormat1 = metricFormat.copy();
        assertEquals(metricFormat1, metricFormat);
    }

    @Test
    void testEqualsFalse() {
        MetricFormat metricFormat1 = new MetricFormat(100, 25);
        assertNotEquals(metricFormat1, metricFormat);
    }

    @Test
    void copy() {
        MetricFormat metricFormat1 = metricFormat.copy();
        assertEquals(metricFormat1, metricFormat);
    }

    @Test
    void getSize() {
        String expectedValue = String.format("%fcm x %fcm", 120.0, 25.0);
        assertEquals(expectedValue, metricFormat.getSize());
    }
}