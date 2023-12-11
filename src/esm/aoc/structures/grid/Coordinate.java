package esm.aoc.structures.grid;

public record Coordinate(long x, long y) {

    public Coordinate move(final Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(x, y + 1);
            case DOWN -> new Coordinate(x, y - 1);
            case LEFT -> new Coordinate(x - 1, y);
            case RIGHT -> new Coordinate(x + 1, y);
            case UP_LEFT -> new Coordinate(x - 1, y + 1);
            case UP_RIGHT -> new Coordinate(x + 1, y + 1);
            case DOWN_LEFT -> new Coordinate(x - 1, y - 1);
            case DOWN_RIGHT -> new Coordinate(x + 1, y - 1);
        };
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    public long distance(final Coordinate other) {
        return Math.max(y, other.y) - Math.min(y, other.y) +
               Math.max(x, other.x) - Math.min(x, other.x);
    }

}
