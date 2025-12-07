package esm.aoc.y25.day07;

import java.util.Map;
import java.util.stream.Collectors;

import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.Grid;

public class Tachyon {

    private final Grid<GridSpace> grid;

    public Tachyon(final CharGrid grid) {
        this.grid = toCountingGrid(grid);
        filterDown();
    }

    public long getSplittersHit() {
        // Count the number of splitters with beams above them.
        return grid.filterByValue(GridSpace::isSplitter)
            .stream()
            .filter(c -> grid.get(c.move(Direction.UP)).isBeam())
            .count();
    }

    public long getAlternateTimelines() {
        // Count the number of beams along the bottom row, and the total of ways they could have got there.
        return grid.getRow(0).stream()
            .filter(c -> grid.get(c).isBeam())
            .mapToLong(c -> grid.get(c).count())
            .sum();
    }

    private void filterDown() {
        final Coordinate start = grid.filterByValue(GridSpace::isStart).iterator().next();
        grid.put(start, new GridSpace("|", 1L));
        for (long y = start.y() - 1; y >= 0; y--) {
            continueDown(y);
        }
    }

    private void continueDown(final long level) {
        grid.getRow(level + 1)
            .stream()
            .filter(c -> grid.get(c).isBeam())
            .forEach(this::moveBeamDown);
    }

    private void moveBeamDown(final Coordinate from) {
        final Coordinate spaceBelow = from.move(Direction.DOWN);
        final GridSpace below = grid.get(spaceBelow);
        if (below.isSpace() || below.isBeam()) {
            setBeam(from, spaceBelow);
        } else if (below.isSplitter()) {
            final Coordinate left = spaceBelow.move(Direction.LEFT);
            final Coordinate right = spaceBelow.move(Direction.RIGHT);
            setBeam(from, left);
            setBeam(from, right);
        }
    }

    private void setBeam(final Coordinate from, final Coordinate to) {
        if (!grid.contains(to)) {
            return;
        }
        final var spaceFrom  = grid.get(from);
        final var spaceTo  = grid.get(to);
        if (spaceTo.isSpace()) {
            grid.put(to, new GridSpace("|", spaceFrom.count()));
        } else if (spaceTo.isBeam()) {
            grid.put(to, new GridSpace("|", spaceFrom.count() + spaceTo.count()));
        }
    }

    private static Grid<GridSpace> toCountingGrid(final CharGrid charGrid) {
        final var map = charGrid.getMap().entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> new GridSpace(e.getValue(), 0L)
            ));

        return new Grid<>(map);
    }

    private record GridSpace(String value, Long count) {
        boolean isStart() {
            return "S".equals(value);
        }
        boolean isSplitter() {
            return "^".equals(value);
        }
        boolean isBeam() {
            return "|".equals(value);
        }
        boolean isSpace() {
            return ".".equals(value);
        }
    }
}
