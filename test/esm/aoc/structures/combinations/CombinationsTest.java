package esm.aoc.structures.combinations;

import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CombinationsTest {

    @Test
    void testChooseTwo() {
        final var things = Set.of("a", "b", "c", "d");
        final var pairs = Combinations.chooseN(things, 2);
        assertEquals(pairs, Set.of(
                Set.of("a", "b"),
                Set.of("a", "c"),
                Set.of("a", "d"),
                Set.of("b", "c"),
                Set.of("b", "d"),
                Set.of("c", "d")
        ));
    }

    @Test
    void testChooseThree() {
        final var things = Set.of("a", "b", "c", "d");
        final var pairs = Combinations.chooseN(things, 3);
        assertEquals(pairs, Set.of(
                Set.of("a", "b", "c"),
                Set.of("a", "b", "d"),
                Set.of("a", "c", "d"),
                Set.of("b", "c", "d")
        ));
    }

    @Test
    void factorial() {
        assertEquals(1, Combinations.factorial(1));
        assertEquals(2, Combinations.factorial(2));
        assertEquals(6, Combinations.factorial(3));
        assertEquals(24, Combinations.factorial(4));
        assertEquals(120, Combinations.factorial(5));
    }

    @Test
    void testNumberOfCombinations() {
        assertEquals(252, Combinations.numberOfCombinations(10, 5));
    }
}