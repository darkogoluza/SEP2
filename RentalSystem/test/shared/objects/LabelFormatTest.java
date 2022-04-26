package shared.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelFormatTest {
    private LabelFormat labelFormat = null;

    @BeforeEach
    void setUp() {
        labelFormat = new LabelFormat("XL");
    }

    @Test
    void testToString() {
        assertEquals("XL", labelFormat.toString());
    }

    @Test
    void testEqualsTrue() {
        LabelFormat labelFormat1 = labelFormat.copy();
        assertEquals(labelFormat1, labelFormat);
    }

    @Test
    void testEqualsFalse() {
        LabelFormat labelFormat1 = new LabelFormat("L");
        assertNotEquals(labelFormat1, labelFormat);
    }

    @Test
    void copy() {
        LabelFormat labelFormat1 = labelFormat.copy();
        assertEquals(labelFormat1, labelFormat);
    }

    @Test
    void getSize() {
        assertEquals("XL", labelFormat.toString());
    }
}