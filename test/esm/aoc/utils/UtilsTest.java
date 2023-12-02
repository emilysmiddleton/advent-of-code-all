package esm.aoc.utils;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testRemoveFirst() {
        final var things = List.of(1, 2, 3, 4);
        final var sublist = Utils.removeFirst(things);
        assertEquals(List.of(2, 3, 4), sublist);
    }
}