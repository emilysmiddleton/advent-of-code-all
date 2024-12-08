package esm.aoc.y24.d08;

import java.util.LinkedHashSet;
import java.util.Set;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.PredicateSet;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;

public class MainY24D08 {

    public static void main(String[] args) {
        final var grid = InputReader.readCharGrid("2024/day08.txt");
        System.out.println(STR."Part 1: \{solve(false, grid)}");
        System.out.println(STR."Part 2: \{solve(true, grid)}");
    }

    private static long solve(final boolean includeAll, final CharGrid grid) {
        final Set<Coordinate> nodes = new LinkedHashSet<>();
        final var antenna = grid.filterByValue(s -> !".".equals(s));
        for (final var c1 : antenna) {
            for (final var c2 : antenna) {
                if (grid.get(c1).equals(grid.get(c2)) && c1.x() < c2.x()) {
                    nodes.addAll(getAntiNodes(includeAll, grid, c1, c2));
                }
            }
        }
        return nodes.size();
    }

    private static Set<Coordinate> getAntiNodes(final boolean includeAll, final CharGrid grid, final Coordinate c1, final Coordinate c2) {
        final PredicateSet<Coordinate> nodes = new PredicateSet<>(grid::contains);
        final var diff = c1.getDiff(c2);
        if (includeAll) {
            var left = c1;
            var right = c2;
            while (grid.contains(left)) {
                nodes.add(left);
                left = left.subtract(diff);
            }
            while (grid.contains(right)) {
                nodes.add(right);
                right = right.add(diff);
            }
        } else {
            nodes.add(c1.subtract(diff));
            nodes.add(c2.add(diff));
        }
        return nodes.getItems();
    }

}
