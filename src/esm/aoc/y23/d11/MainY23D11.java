package esm.aoc.y23.d11;

import esm.aoc.input.InputReader;
import esm.aoc.structures.combinations.Combinations;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Grid;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainY23D11 {

    public static void main(String[] args) {
        final long part1 = solve(1);
        final long part2 = solve(999999);
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    private static long solve(final long factor) {
        var grid = InputReader.readGrid("2023/day11.txt", s -> ".".equals(s) ? null : s);
        grid = expandVertically(grid, factor);
        grid = expandHorizontally(grid, factor);
        return Combinations.getCombinations(grid.getCoordinates())
                           .stream()
                           .mapToLong(pair -> pair.left().distance(pair.right()))
                           .sum() / 2;
    }

    private static Grid<String> expandVertically(final Grid<String> grid, final long factor) {
        final Map<Coordinate, String> map = new LinkedHashMap<>();
        int expansion = 0;
        for (long y = grid.getMinY(); y <= grid.getMaxY(); y++) {
            final long newY = y + expansion;
            final var onRow = grid.getRow(y);
            if (onRow.isEmpty()) {
                expansion += factor;
            }
            onRow.forEach(c -> map.put(new Coordinate(c.x(), newY), "#"));
        }
        return new Grid<>(map);
    }

    private static Grid<String> expandHorizontally(final Grid<String> grid, final long factor) {
        final Map<Coordinate, String> map = new LinkedHashMap<>();
        int expansion = 0;
        for (long x = grid.getMinX(); x <= grid.getMaxX(); x++) {
            final long newX = x + expansion;
            final var onColumn = grid.getColumn(x);
            if (onColumn.isEmpty()) {
                expansion += factor;
            }
            onColumn.forEach(c -> map.put(new Coordinate(newX, c.y()), "#"));
        }
        return new Grid<>(map);
    }

}
