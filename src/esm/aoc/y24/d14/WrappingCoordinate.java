package esm.aoc.y24.d14;

import esm.aoc.structures.grid.Coordinate;

public class WrappingCoordinate {

    private final Coordinate position;
    private final int width;
    private final int height;

    public static void main(String[] args) {
        final WrappingCoordinate c = new WrappingCoordinate(new Coordinate(31, 100), 101, 103);
        final Coordinate v = new Coordinate(-36,-71);
        System.out.println(c.add(v, 100));
    }

    public WrappingCoordinate(final Coordinate position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public WrappingCoordinate add(final Coordinate other, final int count) {
        var next = this;
        for (int i = 0; i < count; i++) {
            next = next.add(other);
        }
        return next;
    }

    public WrappingCoordinate add(final Coordinate other) {
        return new WrappingCoordinate(
                new Coordinate(
                wrap(position.x(), other.x(), width),
                wrap(position.y(), other.y(), height)),
                width, height
        );
    }

    private long wrap(final long a, final long b, final int size) {
        long next = a + b;
        if (next < 0) {
            next += size;
        }
        if (next >= size) {
            next -= size;
        }
        return next;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    public long x() {
        return position.x();
    }

    public long y() {
        return position.y();
    }

    public Coordinate getPosition() {
        return position;
    }
}
