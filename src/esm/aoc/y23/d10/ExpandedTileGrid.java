package esm.aoc.y23.d10;

import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Grid;
import java.util.LinkedHashMap;
import java.util.Map;

public record ExpandedTileGrid(Grid<String> grid) {

    public static ExpandedTileGrid expand(final TileGrid tileGrid) {
        final var grid = tileGrid.getGrid();
        final Map<Coordinate, String> map = new LinkedHashMap<>();
        for (final var coordinate : tileGrid.findLoop()) {
            final var tile = grid.get(coordinate);
            int newX = coordinate.x() * 3;
            int newY = coordinate.y() * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.put(new Coordinate(newX + i, newY + j), tile.tile().get(i, j));
                }
            }
        }
        final var newGrid = new Grid<>(map);
        newGrid.fillGaps(".");
        return new ExpandedTileGrid(newGrid);
    }

    @Override
    public String toString() {
        return grid.toString();
    }

    public Coordinate getStart() {
        return new Coordinate(grid.getMinX(), grid.getMinY());
    }

}
