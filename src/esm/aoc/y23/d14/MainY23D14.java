package esm.aoc.y23.d14;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MainY23D14 {

    public static void main(String[] args) {
        final var grid = InputReader.readCharGrid("2023/day14.txt");
        final var loop = loopUntilInCycle(grid);
        final var cyclesLeft = (1000000000 - loop.left() - loop.right()) % loop.right();
        for (int i = 0; i < cyclesLeft; i++) {
            cycle(grid);
        }
        System.out.println(calculateLoad(grid));
    }

    private static long calculateLoad(final CharGrid grid) {
        return grid.filterByValue("O"::equals).stream().mapToLong(c -> c.y() + 1).sum();
    }

    private static Pair<Integer, Integer> loopUntilInCycle(final CharGrid grid) {
        int loop = 0;
        final Map<Set<Coordinate>, Integer> seen = new LinkedHashMap<>();
        seen.put(grid.filterByValue("O"::equals), 0);
        while (true) {
            loop++;
            cycle(grid);
            final var positions = grid.filterByValue("O"::equals);
            if (seen.containsKey(positions)) {
                final int firstSeen = seen.get(positions);
                return new Pair<>(firstSeen, loop - firstSeen);
            } else {
                seen.put(positions, loop);
            }
        }
    }

    private static void cycle(final CharGrid grid) {
        move(grid, Direction.UP);
        move(grid, Direction.LEFT);
        move(grid, Direction.DOWN);
        move(grid, Direction.RIGHT);
    }

    private static void move(final CharGrid grid, final Direction direction) {
        final var coordinates = new ArrayList<>(grid.filterByValue("O"::equals));
        coordinates.sort(COMPARATORS.get(direction));
        for (final var coordinate : coordinates) {
            var current = coordinate;
            var moved = true;
            while (moved) {
                final var movedTo = move(grid, current, direction);
                moved = !movedTo.equals(current);
                current = movedTo;
            }
        }
    }

    private static Coordinate move(final CharGrid grid, final Coordinate coordinate, final Direction direction) {
        final var next = coordinate.move(direction);
        if (".".equals(grid.get(next))) {
            grid.put(next, "O");
            grid.put(coordinate, ".");
            return next;
        }
        return coordinate;
    }

    private static final Map<Direction, Comparator<Coordinate>> COMPARATORS = Map.of(
            Direction.UP, Comparator.comparing(Coordinate::y).reversed(),
            Direction.DOWN, Comparator.comparing(Coordinate::y),
            Direction.RIGHT, Comparator.comparing(Coordinate::x).reversed(),
            Direction.LEFT, Comparator.comparing(Coordinate::x)
    );
}
