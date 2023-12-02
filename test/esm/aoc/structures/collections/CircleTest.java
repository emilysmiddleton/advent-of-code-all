package esm.aoc.structures.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void testGet() {
        final Circle<String> circle = Circle.of("a", "b", "c");
        assertEquals("a", circle.get(-3));
        assertEquals("b", circle.get(-2));
        assertEquals("c", circle.get(-1));
        assertEquals("a", circle.get(0));
        assertEquals("b", circle.get(1));
        assertEquals("c", circle.get(2));
        assertEquals("a", circle.get(3));
        assertEquals("b", circle.get(4));
        assertEquals("c", circle.get(5));
    }
}