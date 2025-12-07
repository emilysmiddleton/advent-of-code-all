package esm.aoc.y25.day04;

import java.util.Set;
import java.util.stream.Collectors;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;

public class MainY25D04 {

    public static void main(String[] args) {
        final CharGrid grid = InputReader.readCharGrid("2025/day04.txt");
        System.out.println(STR."Part 1: \{solve1(grid)}");
        System.out.println(STR."Part 1: \{solve2(grid)}");
    }

    private static long solve1(final CharGrid grid) {
        return getAccessible(grid).size();
    }

    private static long solve2(final CharGrid grid) {
        Set<Coordinate> accessible = getAccessible(grid);
        while (!accessible.isEmpty()) {
            accessible.forEach(c-> grid.put(c, "x"));
            accessible = getAccessible(grid);
        }
        return grid.filterByValue("x"::equals).size();
    }

    private static Set<Coordinate> getAccessible(final CharGrid grid) {
        return grid.getCoordinates().stream().filter(c-> canAccess(grid, c)).collect(Collectors.toSet());
    }

    private static boolean canAccess(final CharGrid grid, final Coordinate coordinate) {
        return grid.get(coordinate).equals("@") && grid.getAdjacent(coordinate, true).entrySet().stream()
            .filter(entry -> entry.getValue().equals("@")).count() < 4;
    }

}
