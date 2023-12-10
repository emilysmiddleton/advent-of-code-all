package esm.aoc.y23.d10;

import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.Grid;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public record TileGrid(Grid<Tile> grid) {

    public Coordinate getStart() {
        return grid.filterByValue(tile -> "S".equals(tile.type())).get(0);
    }

    public Set<Coordinate> findLoop() {
        final Set<Coordinate> loop = new LinkedHashSet<>();
        var current = getStart();
        loop.add(current);
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
