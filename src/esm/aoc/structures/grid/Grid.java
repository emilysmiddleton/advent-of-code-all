package esm.aoc.structures.grid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import static esm.aoc.structures.grid.Direction.DOWN;
import static esm.aoc.structures.grid.Direction.LEFT;
import static esm.aoc.structures.grid.Direction.RIGHT;
import static esm.aoc.structures.grid.Direction.UP;

public class Grid<T> {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    private final Map<Coordinate, T> map;

    public Grid(final Map<Coordinate, T> map) {
        this.map = map;
        minX = map.keySet().stream().mapToInt(Coordinate::x).min().orElseThrow();
        maxX = map.keySet().stream().mapToInt(Coordinate::x).max().orElseThrow();
        minY = map.keySet().stream().mapToInt(Coordinate::y).min().orElseThrow();
        maxY = map.keySet().stream().mapToInt(Coordinate::y).max().orElseThrow();
    }

    public Map<Coordinate, T> getMap() {
        return map;
    }

    public T get(final Coordinate coordinate) {
        return map.get(coordinate);
    }

    public T get(final int x, final int y) {
        return get(new Coordinate(x, y));
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

    public void put(final Coordinate coordinate, final T value) {
        map.put(coordinate, value);
        minX = Math.min(minX, coordinate.x());
        maxX = Math.max(maxX, coordinate.x());
        minY = Math.min(minY, coordinate.y());
        maxY = Math.max(maxY, coordinate.y());
    }
    @Override
    public String toString() {
        final var builder = new StringBuilder();
        for (int y= maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                final var value = get(x, y);
                builder.append(value == null ? " " : value);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public T get(final Coordinate coordinate, final Direction direction) {
        return get(coordinate.move(direction));
    }

    public void fillGaps(final T value) {
        for (int i = minX; i <= maxX; i++) {
            for (int j = minX; j <= maxY; j++) {
                if (!map.containsKey(new Coordinate(i, j))) {
                    map.put(new Coordinate(i, j), value);
                }
            }
        }
    }

    public List<Coordinate> filterByValue(Predicate<T> predicate) {
        return map.entrySet().stream().filter(e -> predicate.test(e.getValue())).map(Map.Entry::getKey).toList();
    }

}
