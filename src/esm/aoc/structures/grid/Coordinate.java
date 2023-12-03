package esm.aoc.structures.grid;

public record Coordinate(int x, int y) {

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

}
