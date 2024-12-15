package esm.aoc.y24.d15;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class DoubleWidthGrid extends Warehouse {

    public DoubleWidthGrid(final CharGrid grid) {
        super(expand(grid), "[", "]");
        System.out.println(this.grid);
    }

    @Override
    public List<Pair<Coordinate, String>> getConnectedBoxes(final Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            return super.getConnectedBoxes(direction);
        }
        final List<Pair<Coordinate, String>> allConnected = new ArrayList<>();
        List<Pair<Coordinate, String>> row = getFullBox(position.move(direction));
        while(!row.isEmpty()) {
            allConnected.addAll(row);
            final List<Pair<Coordinate, String>> nextRow = new ArrayList<>();
            for (final var pair : row) {
                final var connected = pair.left().move(direction);
                if (isBox(connected)) {
                    nextRow.addAll(getFullBox(connected));
                }
            }
            row = nextRow;
        }
        return allConnected;
    }

    private List<Pair<Coordinate, String>> getFullBox(final Coordinate coordinate) {
        if ("[".equals(grid.get(coordinate))) {
            return List.of(new Pair<>(coordinate, "["), new Pair<>(coordinate.move(Direction.RIGHT), "]"));
        }
        if ("]".equals(grid.get(coordinate))) {
            return List.of(new Pair<>(coordinate, "]"), new Pair<>(coordinate.move(Direction.LEFT), "["));
        }
        throw new IllegalStateException();
    }

    private static CharGrid expand(final CharGrid grid) {
        final Map<Coordinate, String> expanded = new LinkedHashMap<>();
        for (final var entry : grid.getMap().entrySet()) {
            final var left = new Coordinate(entry.getKey().x() * 2, entry.getKey().y());
            final var right = new Coordinate(left.x() + 1, entry.getKey().y());
            switch (entry.getValue()) {
                case "#": {
                    expanded.put(left, "#");
                    expanded.put(right, "#");
                    break;
                }
                case "O": {
                    expanded.put(left, "[");
                    expanded.put(right, "]");
                    break;
                }
                case ".": {
                    expanded.put(left, ".");
                    expanded.put(right, ".");
                    break;
                }
                case "@": {
                    expanded.put(left, "@");
                    expanded.put(right, ".");
                    break;
                }
            }
        }
        return new CharGrid(expanded);
    }

}
