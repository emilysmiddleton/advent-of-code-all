package esm.aoc.structures.grid.tree;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import esm.aoc.structures.collections.Utils;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.tree.DepthFirstIterator;

public class Pathfinder {

    private final CharGrid grid;
    private final Coordinate start;
    private final Set<String> movable;
    private final Predicate<Coordinate> end;

    public Pathfinder(
            final CharGrid grid,
            final Coordinate start,
            final Set<String> movable,
            final Predicate<Coordinate> end
    ) {
        this.grid = grid;
        this.start = start;
        this.movable = movable;
        this.end = end;
    }

    public Optional<List<Coordinate>> findPath(final int maxLength) {
        return findPaths(maxLength, true).stream().findFirst();
    }

    public Set<List<Coordinate>> findPaths(final int maxLength) {
        return findPaths(maxLength, false);
    }

    private Set<List<Coordinate>> findPaths(final int maxLength, final boolean findFirst) {
        final var rootState = getRootState(maxLength);
        final var root = new GridTreeNode(rootState);
        final var iterator = new DepthFirstIterator<>(root);
        final Set<List<Coordinate>> paths = new LinkedHashSet<>();
        while (iterator.hasNext()) {
            final GridState next = iterator.next().state();
            if (end.test(next.getPosition())) {
                if (findFirst) {
                    return Collections.singleton(next.getPath());
                } else {
                    paths.add(next.getPath());
                }
            }
        }
        return paths;
    }

    private GridState getRootState(final int maxLength) {
        Predicate<GridState> canMove = state -> {
            var position = state.getPosition();
            if (end.test(position)) {
                return true;
            }
            if (state.getPath().indexOf(position) < state.getPath().size() - 1) {
                return false;
            }
            if (state.getPath().size() >= maxLength) {
                return false;
            }
            return grid.contains(position) && movable.contains(grid.get(position));
        };
        return new GridState(grid, start, end, canMove, Utils.addAndCopy(Collections.emptyList(), start));
    }
}
