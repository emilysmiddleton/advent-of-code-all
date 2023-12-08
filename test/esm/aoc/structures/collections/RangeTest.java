package esm.aoc.structures.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RangeTest {

    @Test
    void overlaps() {
        assertFalse(new Range(0, 6).overlaps(new Range(8, 9)));
        assertTrue(new Range(0, 6).overlaps(new Range(6, 9)));
        assertTrue(new Range(0, 6).overlaps(new Range(4, 9)));
        assertFalse(new Range(8, 9).overlaps(new Range(0, 6)));
        assertTrue(new Range(6, 9).overlaps(new Range(0, 6)));
        assertTrue(new Range(4, 9).overlaps(new Range(0, 6)));
    }

    @Test
    void getOverlap() {
        assertEquals(new Range(6, 6), new Range(0, 6).getOverlap(new Range(6, 9)));
        assertEquals(new Range(4, 6), new Range(0, 6).getOverlap(new Range(4, 9)));
        assertEquals(new Range(6, 6), new Range(6, 9).getOverlap(new Range(0, 6)));
        assertEquals(new Range(4, 6), new Range(4, 9).getOverlap(new Range(0, 6)));
    }

}