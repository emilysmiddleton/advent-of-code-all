package esm.aoc.y23.d13;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.Axis;
import esm.aoc.structures.grid.CharGrid;

public class MainY23D13 {

    public static void main(String[] args) {
        final var input = InputReader.readGroups("2023/day13.txt", InputMapper::readCharGrid);
        final var reflections = input.stream().map(MainY23D13::findReflection).toList();
        System.out.println("Part 1: " + reflections.stream().mapToLong(Reflection::getValue).sum());
        final var newReflections = reflections.stream().map(MainY23D13::findWithSmudge).toList();
        System.out.println("Part 2: " + newReflections.stream().mapToLong(Reflection::getValue).sum());
    }


    private static Reflection findWithSmudge(final Reflection reflection) {
        final var grid = reflection.grid();
        for (final var coordinate : grid.getCoordinates()) {
            final var current = grid.get(coordinate);
            final var flipped = current.equals("#") ? "." : "#";
            grid.put(coordinate, flipped);
            final var newReflection = findReflection(grid, reflection);
            grid.put(coordinate, current);
            if (newReflection != null) {
                return newReflection;
            }
        }
        throw new IllegalStateException();
    }

    private static Reflection findReflection(final CharGrid grid) {
        return findReflection(grid, null);
    }

    private static Reflection findReflection(final CharGrid grid, final Reflection toIgnore) {
        final var ignoreVertical = toIgnore != null && toIgnore.axis() == Axis.VERTICAL ? toIgnore.line() : -1;
        final var ignoreHorizontal = toIgnore != null && toIgnore.axis() == Axis.HORIZONTAL ? toIgnore.line() : -1;
        final var vertical = findVerticalReflection(grid, ignoreVertical);
        if (vertical != null) {
            return vertical;
        }
        final var horizontal = findHorizontalReflection(grid, ignoreHorizontal);
        if (horizontal != null) {
            return horizontal;
        }
        return null;
    }


    private static Reflection findVerticalReflection(final CharGrid grid, final long toIgnore) {
        for (long x = grid.getMinX(); x < grid.getMaxX(); x++) {
            if (x != toIgnore && reflectsVertically(grid, x)) {
                return new Reflection(grid, x, Axis.VERTICAL);
            }
        }
        return null;
    }

    private static Reflection findHorizontalReflection(final CharGrid grid, final long toIgnore) {
        for (long y = grid.getMinY(); y < grid.getMaxY(); y++) {
            if (y != toIgnore && reflectsHorizontally(grid, y)) {
                return new Reflection(grid, y, Axis.HORIZONTAL);
            }
        }
        return null;
    }

    private static boolean reflectsVertically(final CharGrid grid, final long column) {
        for (int i = 0; i <= column && column + i + 1 <= grid.getMaxX(); i++) {
            final var left = grid.getColumnValues(column - i);
            final var right = grid.getColumnValues(column + i + 1);
            if (!left.equals(right)) {
                return false;
            }
        }
        return true;
    }

    private static boolean reflectsHorizontally(final CharGrid grid, final long row) {
        for (int i = 0; i <= row && row + i + 1 <= grid.getMaxY(); i++) {
            final var left = grid.getRowValues(row - i);
            final var right = grid.getRowValues(row + i + 1);
            if (!left.equals(right)) {
                return false;
            }
        }
        return true;
    }

}
