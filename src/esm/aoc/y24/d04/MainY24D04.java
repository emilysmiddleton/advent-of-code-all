package esm.aoc.y24.d04;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class MainY24D04 {

    public static void main(String[] args) {
        final var grid = InputReader.readCharGrid("2024/day04.txt");
        System.out.println(STR."Part 1: \{solve1(grid)}");
        System.out.println(STR."Part 2: \{solve2(grid)}");
    }

    private static long solve1(final CharGrid grid) {
        return grid.getCoordinates().stream().mapToLong(c -> count(grid, c)).sum();
    }

    private static long count(final CharGrid grid, final Coordinate coordinate) {
        long count = 0;
        final var startChar = grid.get(coordinate);
        for (final var direction : Direction.getValues(true)) {
            final String word = startChar +
                    grid.get(coordinate.move(direction, 1)) +
                    grid.get(coordinate.move(direction, 2)) +
                    grid.get(coordinate.move(direction, 3));
            if ("XMAS".equals(word)) {
                count++;
            }
        }
        return count;
    }

    private static long solve2(final CharGrid grid) {
        return grid.getCoordinates().stream().filter(c -> isX(grid, c)).count();
    }

    private static boolean isX(final CharGrid grid, final Coordinate coordinate) {
        final var startChar = grid.get(coordinate);
        final var topLeft = grid.get(coordinate.move(Direction.UP_LEFT));
        final var topRight = grid.get(coordinate.move(Direction.UP_RIGHT));
        final var bottomLeft = grid.get(coordinate.move(Direction.DOWN_LEFT));
        final var bottomRight = grid.get(coordinate.move(Direction.DOWN_RIGHT));
        final var topLeftToBottomRight =
                ("M".equals(topLeft) && "S".equals(bottomRight)) || ("S".equals(topLeft) && "M".equals(bottomRight));
        final var topRightToBottomLeft =
                ("M".equals(topRight) && "S".equals(bottomLeft)) || ("S".equals(topRight) && "M".equals(bottomLeft));
        return "A".equals(startChar) && topLeftToBottomRight && topRightToBottomLeft;
    }

}
