package esm.aoc.structures.grid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static esm.aoc.structures.grid.Direction.DOWN;
import static esm.aoc.structures.grid.Direction.LEFT;
import static esm.aoc.structures.grid.Direction.RIGHT;
import static esm.aoc.structures.grid.Direction.UP;

public class Grid<T> {

    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

    private final Map<Coordinate, T> map;

    public Grid(final Map<Coordinate, T> map) {
        this.map = map;
        minX = map.keySet().stream().mapToInt(Coordinate::x).min().orElseThrow();
        maxX = map.keySet().stream().mapToInt(Coordinate::x).max().orElseThrow();
        minY = map.keySet().stream().mapToInt(Coordinate::y).min().orElseThrow();
        maxY = map.keySet().stream().mapToInt(Coordinate::y).max().orElseThrow();
    }

    public T get(final Coordinate coordinate) {
        return map.get(coordinate);
    }

    public T getOrDefault(final Coordinate coordinate, final T defaultValue) {
        if (map.containsKey(coordinate)) {
            return get(coordinate);
        }
        return defaultValue;
    }

    public T get(final int x, final int y) {
        return get(new Coordinate(x, y));
    }

    public T getOrDefault(final int x, final int y, final T defaultValue) {
        return getOrDefault(new Coordinate(x, y), defaultValue);
    }

    public Map<Coordinate, T> getAdjacent(final Coordinate coordinate, final boolean includeDiagonal) {
        final List<Direction> directions = includeDiagonal ?
                List.of(Direction.values()) :
                List.of(UP, DOWN, LEFT, RIGHT);
        final Map<Coordinate, T> adjacent = new LinkedHashMap<>();
        for (final var direction : directions) {
            final var nextCoordinate = coordinate.move(direction);
            if (map.containsKey(nextCoordinate)) {
                adjacent.put(nextCoordinate, get(nextCoordinate));
            }
        }
        return adjacent;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
