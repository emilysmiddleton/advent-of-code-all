package esm.aoc.structures.grid.tree;

import java.util.List;
import java.util.function.Predicate;

import esm.aoc.structures.collections.Utils;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class GridState {

    private final CharGrid grid;
    private final Coordinate position;
    private final Predicate<Coordinate> end;
    private final Predicate<GridState> canMove;
    private final List<Coordinate> path;

    public GridState(
        final CharGrid grid,
        final Coordinate position,
        final Predicate<Coordinate> end,
        final Predicate<GridState> canMove,
        final List<Coordinate> path
    ) {
        this.grid = grid;
        this.position = position;
        this.end = end;
        this.canMove = canMove;
        this.path = path;
    }

    public Coordinate getPosition() {
        return position;
    }

    public CharGrid getGrid() {
        return grid;
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public boolean isValid() {
        return canMove.test(this);
    }

    public String getValue() {
        return grid.get(position);
    }

    public GridState getNextState(final Direction direction) {
        final var next = position.move(direction);
        return new GridState(grid, next, end, canMove, Utils.addAndCopy(path, next));
    }

    public boolean isEnd() {
        return end.test(position);
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
