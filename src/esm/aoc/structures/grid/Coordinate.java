package esm.aoc.structures.grid;

public record Coordinate(int x, int y) {

    public Coordinate move(final Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(x, y + 1);
            case DOWN -> new Coordinate(x, y - 1);
            case LEFT -> new Coordinate(x - 1, y);
            case RIGHT -> new Coordinate(x + 1, y);
        };
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

}
