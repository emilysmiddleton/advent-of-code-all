package esm.aoc.structures.grid;

public record Coordinate(long x, long y) {

    public Coordinate move(final Direction direction) {
        return move(direction, 1);
    }

    public Coordinate move(final Direction direction, final int size) {
        return switch (direction) {
            case UP -> new Coordinate(x, y + size);
            case DOWN -> new Coordinate(x, y - size);
            case LEFT -> new Coordinate(x - size, y);
            case RIGHT -> new Coordinate(x + size, y);
            case UP_LEFT -> new Coordinate(x - size, y + size);
            case UP_RIGHT -> new Coordinate(x + size, y + size);
            case DOWN_LEFT -> new Coordinate(x - size, y - size);
            case DOWN_RIGHT -> new Coordinate(x + size, y - size);
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

    public Coordinate getDiff(final Coordinate other) {
        return new Coordinate(other.x - x, other.y - y);
    }

    public Coordinate add(final Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }


    public Coordinate subtract(final Coordinate other) {
        return new Coordinate(x - other.x, y - other.y);
    }

}
