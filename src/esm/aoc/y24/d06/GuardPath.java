package esm.aoc.y24.d06;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class GuardPath implements Iterator<Pair<Coordinate, Direction>> {

    private final CharGrid grid;
    private final List<Pair<Coordinate, Direction>> path = new ArrayList<>();
    private Coordinate position;
    private Direction direction;

    public GuardPath(final CharGrid grid, final Coordinate position, final Direction direction) {
        this.grid = grid;
        this.position = position;
        this.direction = direction;
        path.add(new Pair<>(position, direction));
    }

    @Override
    public Pair<Coordinate, Direction> next() {
        var next = position.move(direction);
        if (!"#".equals(grid.get(next))) {
            path.add(new Pair<>(next, direction));
            position = next;
            return path.getLast();
        }
        direction = direction.clockwise().clockwise();
        return next();
    }

    @Override
    public boolean hasNext() {
        return inGrid() && !isLoop();
    }

    public List<Pair<Coordinate, Direction>> getPath() {
        return path;
    }

    public boolean isLoop() {
        final var last = path.getLast();
        return inGrid() && path.indexOf(last) < path.lastIndexOf(last);
    }

    public void setObstacle(final Coordinate c) {
        grid.put(c, "#");
    }

    private boolean inGrid() {
        return grid.contains(path.getLast().left());
    }

    public Set<Coordinate> getSpacesInPath() {
        return getPath().stream().map(Pair::left)
                .filter(grid::contains)
                .filter(c -> !path.getFirst().left().equals(c))
                .collect(Collectors.toSet());
    }
}
