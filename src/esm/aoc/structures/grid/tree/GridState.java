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
    private final Predicate<GridState> canMove;
    private final List<Coordinate> path;

    public GridState(
        final CharGrid grid,
        final Coordinate position,
        final Predicate<GridState> canMove,
        final List<Coordinate> path
    ) {
        this.grid = grid;
        this.position = position;
        this.canMove = canMove;
        this.path = path;
    }

    public Coordinate getPosition() {
        return position;
    }

    public CharGrid getGrid() {
        return grid;
    }

    public Predicate<GridState> getCanMove() {
        return canMove;
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
        return new GridState(grid, next, canMove, Utils.addAndCopy(path, next));
    }

}
