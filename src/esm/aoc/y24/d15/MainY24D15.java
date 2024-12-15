package esm.aoc.y24.d15;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class MainY24D15 {

    public static void main(String[] args) throws InterruptedException {
        final var grid = InputReader.readCharGrid("2024/day15_1.txt");
        final var directions = InputReader.readSeparated("2024/day15_2.txt", "").stream().flatMap(Collection::stream).map(Direction::parse).toList();
        System.out.println(STR."Part 1: \{solve(grid, directions)}");
       // System.out.println(STR."Part 2: \{solve(robots)}");
    }

    private static long solve(final CharGrid grid, List<Direction> directions) {
        Coordinate position = grid.filterByValue("@"::equals).iterator().next();
        System.out.println(grid);
        for (final var direction : directions) {
            System.out.println(direction);
            final var next = position.move(direction);
            final var square = grid.get(next);
            switch (square) {
                case ".": {
                    grid.put(position, ".");
                    grid.put(next, "@");
                    position = next;
                    break;
                }
                case "O": {
                    final var nextSpace = findNextSpace(grid, next, direction);
                    if (nextSpace.isPresent()) {
                        grid.put(position, ".");
                        grid.put(next, "@");
                        grid.put(nextSpace.get(), "O");
                        position = next;
                    }
                }
            }
            System.out.println(grid);
        }
        return grid.filterByValue("O"::equals).stream().mapToLong(c -> gps(grid, c)).sum();
    }

    private static Optional<Coordinate> findNextSpace(final CharGrid grid, final Coordinate position, final Direction direction) {
        Coordinate next = position;
        while (grid.contains(next)) {
            if (".".equals(grid.get(next))) {
                return Optional.ofNullable(next);
            }
            if ("#".equals(grid.get(next))) {
                return Optional.empty();
            }
            next = next.move(direction);
        }
        return Optional.empty();
    }

    private static long gps(final CharGrid grid, final Coordinate position) {
        return (grid.getMaxY() - position.y()) * 100 + position.x();
    }

}
