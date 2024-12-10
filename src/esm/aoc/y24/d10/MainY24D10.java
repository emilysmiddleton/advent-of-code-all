package esm.aoc.y24.d10;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Grid;
import esm.aoc.structures.tree.DepthFirstIterator;

public class MainY24D10 {

    public static void main(String[] args) {
        final var grid = InputReader.readGrid("2024/day10.txt", Integer::parseInt);
        System.out.println(STR."Part 1: \{solve1(grid)}");
        System.out.println(STR."Part 2: \{solve2(grid)}");
    }

    private static long solve1(final Grid<Integer> grid) {
        return grid.filterByValue(i -> i.equals(0)).stream()
                .map(c -> getPaths(grid, c))
                .mapToLong(paths -> new LinkedHashSet<>(paths).size())
                .sum();
    }

    private static long solve2(final Grid<Integer> grid) {
        return grid.filterByValue(i -> i.equals(0)).stream()
                .map(c -> getPaths(grid, c))
                .mapToLong(List::size)
                .sum();
    }

    private static List<Coordinate> getPaths(final Grid<Integer> grid, final Coordinate start) {
        final var root = new MapNode(grid, start);
        final List<Coordinate> leaves = new ArrayList<>();
        final var iterator = new DepthFirstIterator<>(root, leaf -> {
            if (grid.get(leaf).equals(9)) {
                leaves.add(leaf);
            }
        });
        iterator.iterate();
        return leaves;
    }
}
