package esm.aoc.y24.d20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.tree.Pathfinder;

public class MainY24D20 {

    public static void main(String[] args) throws InterruptedException {
        final var grid = InputReader.readCharGrid("2024/day20.txt");
        System.out.println(STR."Part 1: \{solve(grid, 2)}");
        System.out.println(STR."Part 2: \{solve(grid, 20)}");
    }

    private static long solve(final CharGrid grid, final int limit) {
        final var path = getPath(grid);
        final var pathCoordinates = new LinkedHashSet<>(path);
        long count = 0;
        final List<String> cheats = new ArrayList<>();
        for (final var from : path) {
            for (final var to : getReachable(pathCoordinates, from, limit)) {
                final var saved = path.indexOf(to) - path.indexOf(from) - (int) from.distance(to);
                if (saved >= 100) {
                    count++;
                }
            }
            System.out.println(from + " " + count);
        }
        Collections.sort(cheats);
        cheats.forEach(System.out::println);
        return count;
    }

    private static Set<Coordinate> getReachable(final Set<Coordinate> path, final Coordinate from, final int limit) {
        final Set<Coordinate> reachable = new LinkedHashSet<>();
        for (int i = (int) (from.x() - limit); i <= from.x() + limit; i++) {
            for (int j = (int) (from.y() - limit); j <= from.y() + limit; j++) {
                final var to = new Coordinate(i, j);
                final var distance = from.distance(to);
                if (path.contains(to) && distance >= 2 && distance <= limit) {
                    reachable.add(to);
                }
            }
        }
        return reachable;
    }

    private static List<Coordinate> getPath(final CharGrid grid) {
        Coordinate startPosition = grid.filterByValue("S"::equals).stream().findFirst().orElseThrow();
        Coordinate endPosition = grid.filterByValue("E"::equals).stream().findFirst().orElseThrow();
        final var pathFinder = new Pathfinder(grid, startPosition, Collections.singleton("."), endPosition::equals);
        return pathFinder.findPath(Integer.MAX_VALUE).orElseThrow();
    }

}

