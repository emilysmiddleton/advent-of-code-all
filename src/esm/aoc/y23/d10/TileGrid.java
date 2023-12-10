package esm.aoc.y23.d10;

import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.Grid;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TileGrid {

    private final Grid<Tile> grid;
    private final Coordinate start;

    public TileGrid(final Grid<Tile> grid) {
        this.grid = grid;
        this.start = grid.filterByValue(tile -> "S".equals(tile.type())).get(0);
        grid.put(start, getValueOfStart());
    }

    public Set<Coordinate> findLoop() {
        var current = start;
        final Set<Coordinate> loop = new LinkedHashSet<>();
        loop.add(start);
        boolean complete = false;
        while (!complete) {
            final var connections = getConnections(current, grid.get(current));
            if (!loop.contains(connections.get(0))) {
                current = connections.get(0);
                loop.add(current);
            } else if (!loop.contains(connections.get(1))) {
                current = connections.get(1);
                loop.add(current);
            } else {
                complete = true;
            }
        }
        return loop;
    }

    private Tile getValueOfStart() {
        for (final var possible : Set.of("-", "|", "J", "L", "7", "F")) {
            final var possibleTile = Tile.parseTile(possible);
            final var connections = getConnections(start, possibleTile);
            if (connections.size() == 2) {
                return possibleTile;
            }
        }
        throw new IllegalStateException();
    }

    private List<Coordinate> getConnections(final Coordinate coordinate, final Tile tile) {
        final List<Coordinate> connections = new ArrayList<>();
        for (final var direction : Direction.getValues(false)) {
            final var tileInDirection = grid.get(coordinate, direction);
            if (tileInDirection.connectsAtEdge(direction.opposite()) && tile.connectsAtEdge(direction)) {
                connections.add(coordinate.move(direction));
            }
        }
        return connections;
    }

    public Grid<Tile> getGrid() {
        return grid;
    }

}
