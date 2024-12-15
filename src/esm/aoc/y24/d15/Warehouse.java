package esm.aoc.y24.d15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class Warehouse {

    private final String boxLeft;
    private final String boxRight;
    protected final CharGrid grid;
    private final Set<String> boxChars;
    protected Coordinate position;

    public Warehouse(final CharGrid grid, final String boxLeft, final String boxRight) {
        this.boxLeft = boxLeft;
        this.boxRight = boxRight;
        this.grid = new CharGrid(new LinkedHashMap<>(grid.getMap()));
        this.position = this.grid.filterByValue("@"::equals).iterator().next();
        boxChars = (boxLeft.equals(boxRight)) ? Collections.singleton(boxLeft) : Set.of(boxLeft, boxRight);
    }

    public void move(final Direction direction) {
        final var next = position.move(direction);
        final var square = grid.get(next);
        if (".".equals(square)) {
            moveRobot(direction);
        }
        if (boxLeft.equals(square) || boxRight.equals(square)) {
            final var connectedBoxes = getConnectedBoxes(direction);
            if (connectedBoxes.stream().allMatch(pair -> canMove(pair.left(), direction))) {
                moveBoxes(connectedBoxes, direction);
                moveRobot(direction);
            }
        }
    }

    private void moveRobot(final Direction direction) {
        grid.put(position, ".");
        grid.put(position.move(direction), "@");
        position = position.move(direction);
    }

    private boolean canMove(final Coordinate coordinate, final Direction direction) {
        return Set.of("[", "]", "O", ".").contains(grid.get(coordinate.move(direction)));
    }

    public List<Pair<Coordinate, String>> getConnectedBoxes(final Direction direction) {
        final List<Pair<Coordinate, String>> boxes = new ArrayList<>();
        var next = position.move(direction);
        while (isBox(next)) {
            boxes.add(new Pair<>(next, grid.get(next)));
            next = next.move(direction);
        }
        return boxes;
    }

    protected boolean isBox(final Coordinate coordinate) {
        return boxChars.contains(grid.get(coordinate));
    }

    private void moveBoxes(final List<Pair<Coordinate, String>> boxes, final Direction direction) {
        for (final var pair : boxes) {
            grid.put(pair.left(), ".");
        }
        for (final var pair : boxes) {
            grid.put(pair.left().move(direction), pair.right());
        }
    }

    public long gps() {
        return grid.filterByValue(boxLeft::equals).stream().mapToLong(this::gps).sum();
    }

    private long gps(final Coordinate position) {
        return (grid.getMaxY() - position.y()) * 100 + position.x();
    }

}
