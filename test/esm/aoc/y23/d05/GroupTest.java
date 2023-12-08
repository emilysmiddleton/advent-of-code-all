package esm.aoc.y23.d05;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Range;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupTest {

    @Test
    void test() {
        final var groups = InputReader.readGroups("2023/day05_test.txt", MainY23D05::parseGroup);
        var combined = groups.get(0).combine(groups.get(1));
        Assertions.assertEquals(List.of(
                new Row(new Range(0, 14), 39),
                new Row(new Range(15, 49), -15),
                new Row(new Range(50, 51), -13),
                new Row(new Range(52, 97), 2),
                new Row(new Range(98, 99), -63)
        ), combined.rows());
        combined = combined.combine(groups.get(2));
        Assertions.assertEquals(List.of(
                new Row(new Range(0, 13), 28),
                new Row(new Range(14, 14), 35),
                new Row(new Range(15, 21), 27),
                new Row(new Range(22, 25), 35),
                new Row(new Range(26, 49), -26),
                new Row(new Range(50, 51), -24),
                new Row(new Range(52, 58), -2),
                new Row(new Range(59, 97), 2),
                new Row(new Range(98, 99), -74)
        ), combined.rows());
    }
}