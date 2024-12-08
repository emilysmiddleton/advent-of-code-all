package esm.aoc.structures.grid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static esm.aoc.structures.grid.Direction.DOWN;
import static esm.aoc.structures.grid.Direction.LEFT;
import static esm.aoc.structures.grid.Direction.RIGHT;
import static esm.aoc.structures.grid.Direction.UP;

public class Grid<T> {

    private long minX;
    private long minY;
    private long maxX;
    private long maxY;

    private final Map<Coordinate, T> map;

    public Grid(final Map<Coordinate, T> map) {
        this.map = map;
        minX = map.keySet().stream().mapToLong(Coordinate::x).min().orElseThrow();
        maxX = map.keySet().stream().mapToLong(Coordinate::x).max().orElseThrow();
        minY = map.keySet().stream().mapToLong(Coordinate::y).min().orElseThrow();
        maxY = map.keySet().stream().mapToLong(Coordinate::y).max().orElseThrow();
    }

    public Map<Coordinate, T> getMap() {
        return map;
    }

    public T get(final Coordinate coordinate) {
        return map.get(coordinate);
    }

    public T get(final long x, final long y) {
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

    public long getMinX() {
        return minX;
    }

    public long getMinY() {
        return minY;
    }

    public long getMaxX() {
        return maxX;
    }

    public long getMaxY() {
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
        for (long y = maxY; y >= minY; y--) {
            for (long x = minX; x <= maxX; x++) {
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
        for (long i = minX; i <= maxX; i++) {
            for (long j = minX; j <= maxY; j++) {
                if (!map.containsKey(new Coordinate(i, j))) {
                    map.put(new Coordinate(i, j), value);
                }
            }
        }
    }

    public Set<Coordinate> filterByValue(Predicate<T> predicate) {
        return map.entrySet().stream().filter(e -> predicate.test(e.getValue())).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    public List<Coordinate> filterByKey(Predicate<Coordinate> predicate) {
        return map.keySet().stream().filter(predicate).toList();
    }

    public List<Coordinate> getColumn(final long x) {
        return filterByKey(c -> c.x() == x);
    }

    public List<T> getColumnValues(final long x) {
        return getColumn(x).stream().map(this::get).toList();
    }

    public List<Coordinate> getRow(final long y) {
        return filterByKey(c -> c.y() == y);
    }

    public List<T> getRowValues(final long y) {
        return getRow(y).stream().map(this::get).toList();
    }

    public Set<Coordinate> getCoordinates() {
        return map.keySet();
    }

    public boolean contains(final Coordinate coordinate) {
        return map.containsKey(coordinate);
    }

}
