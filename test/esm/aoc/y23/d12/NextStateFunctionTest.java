package esm.aoc.y23.d12;

import esm.aoc.structures.collections.Pair;
import java.util.List;
import org.junit.jupiter.api.Test;
import static esm.aoc.y23.d12.LineSolver.nextLines;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NextStateFunctionTest {

    @Test
    void testGetGroupsUntilNextHash() {
        final var groups = List.of("???", "?", "##??", "#", "???");
        final var result = LineSolver.getGroupsUntilFirstHash(groups);
        assertEquals(List.of(
                new Pair<>("???", List.of("?", "##??", "#", "???")),
                new Pair<>("?", List.of("##??", "#", "???")),
                new Pair<>("##??", List.of("#", "???"))
        ), result);
    }

    @Test
    void testNextLines() {
        // ???.### 1,1,3
        assertEquals(
                List.of(
                        new Line(List.of("?", "###"), List.of(1, 3))
                ),
                nextLines("???", List.of("###"), 1, List.of(1, 3)));
        // .??..??...?##. 1,1,3
        assertEquals(
                List.of(
                        new Line(List.of("??", "?##"), List.of(1, 3)),
                        new Line(List.of("??", "?##"), List.of(1, 3))
                ),
                nextLines("??", List.of("??", "?##"), 1, List.of(1, 3)));
        // ?#?#?#?#?#?#?#? 1,3,1,6
        assertEquals(
                List.of(
                        new Line(List.of("#?#?#?#?#?#?"), List.of(3, 1, 6))
                ),
                nextLines("?#?#?#?#?#?#?#?", List.of(), 1, List.of(3, 1, 6)));
        // ????.#...#... 4,1,1
        assertEquals(
                List.of(
                        new Line(List.of("#", "#"), List.of(1, 1))
                ),
                nextLines("????", List.of("#", "#"), 4, List.of(1, 1)));
        // ????.######..#####. 1,6,5
        assertEquals(
                List.of(
                        new Line(List.of("??", "######", "#####"), List.of(6, 5)),
                        new Line(List.of("?", "######", "#####"), List.of(6, 5)),
                        new Line(List.of("######", "#####"), List.of(6, 5)),
                        new Line(List.of("######", "#####"), List.of(6, 5))
                ),
                nextLines("????", List.of("######", "#####"), 1, List.of(6, 5)));
        // ?###???????? 3,2,1
        assertEquals(
                List.of(
                        new Line(List.of("???????"), List.of(2, 1))
                ),
                nextLines("?###????????", List.of(), 3, List.of(2, 1)));
    }

    @Test
    void getRemainders() {
        assertEquals(List.of("??", "?", "", ""), LineSolver.getRemainders("?????", 2));
        assertEquals(List.of("?", "", ""), LineSolver.getRemainders("???", 1));
        assertEquals(List.of("?##?", "##?"), LineSolver.getRemainders("??#??##?", 2));
        assertEquals(List.of("??#?", "?#?", "#?", "", ""), LineSolver.getRemainders("?????#?", 2));
        assertEquals(List.of("??#", "?#", "#", ""), LineSolver.getRemainders("?????#", 2));
        assertEquals(List.of("#?#?#?#?#?"), LineSolver.getRemainders("?#?#?#?#?#?#?#?", 3));
    }

    @Test
    void indexOfNextGroupWithHash() {
        final var line = new Line(List.of("??", "???", "?##??", "??#", "###"), List.of(2, 1, 3));
        final var index = LineSolver.indexOfNextGroupWithHash(line);
        assertEquals(2, index);
    }
}