package esm.aoc.y15.d25;

import esm.aoc.structures.grid.Coordinate;
import java.util.Iterator;
import static esm.aoc.structures.grid.Direction.LEFT;
import static esm.aoc.structures.grid.Direction.UP;

public class CoordinateIterator implements Iterator<Coordinate> {

    private Coordinate next;
    private long nextStart;

    public CoordinateIterator(final Coordinate current) {
        this.next = current;
        this.nextStart = current.x() + 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Coordinate next() {
        final var toReturn = next;
        // Move up -> left if possible
        var nextNext = next.move(UP).move(LEFT);
        if (nextNext.x() >= 0 && nextNext.y() >= 0) {
            next = nextNext;
            return toReturn;
        }
        // Otherwise go end start of next x
        next = new Coordinate(nextStart, 0);
        nextStart++;
        return toReturn;
    }
}
