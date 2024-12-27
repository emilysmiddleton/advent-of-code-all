package esm.aoc.y24.d20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Utils;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.tree.GridState;
import esm.aoc.structures.grid.tree.GridTreeNode;
import esm.aoc.structures.tree.DepthFirstIterator;

public class MainY24D20 {

    public static void main(String[] args) throws InterruptedException {
        final var grid = InputReader.readCharGrid("2024/day20.txt");
        System.out.println(STR."Part 1: \{solve1(grid)}");
        System.out.println(STR."Part 2: \{solve2()}");
    }

    private static long solve1(final CharGrid grid) {
        final var path = getPath(grid);
        long count = 0;
        for (final var cheat : grid.filterByValue("#"::equals)) {
            // Horizontal
            final var left = cheat.move(Direction.LEFT);
            final var right = cheat.move(Direction.RIGHT);
            if (path.contains(left) && path.contains(right)) {
                int save = Math.abs(path.indexOf(right) - path.indexOf(left)) - 2;
                if (save >= 100) {
                    count++;
                }
            }
            // Vertical
            final var up = cheat.move(Direction.UP);
            final var down = cheat.move(Direction.DOWN);
            if (path.contains(up) && path.contains(down)) {
                int save = Math.abs(path.indexOf(up) - path.indexOf(down)) - 2;
                if (save >= 100) {
                    count++;
                }
            }
        }
        return count;
    }

    private static List<Coordinate> getPath(final CharGrid grid) {
        Coordinate startPosition = grid.filterByValue("S"::equals).stream().findFirst().orElseThrow();
        final var rootState = getRootState(grid, startPosition);
        final var root = new GridTreeNode(rootState);
        final var iterator = new DepthFirstIterator<>(root);
        final Set<List<Coordinate>> paths = new LinkedHashSet<>();
        while (iterator.hasNext()) {
            final GridState next = iterator.next().state();
            if ("E".equals(next.getValue())) {
                return next.getPath();
            }
        }
        throw new IllegalStateException();
    }

    private static GridState getRootState(final CharGrid grid, final Coordinate startPosition) {
        Predicate<GridState> canMove = state -> {
            var position = state.getPosition();
            if (state.getPath().indexOf(position) < state.getPath().size() - 1) {
                return false;
            }
            var value = grid.get(position);
            return (".".equals(value) || "E".equals(value));
        };
        return new GridState(grid, startPosition, canMove, Utils.addAndCopy(Collections.emptyList(), startPosition));
    }

    private static long solve2() {
        return 0;
    }

}
