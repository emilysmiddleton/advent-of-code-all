package esm.aoc.y23.d05;

import esm.aoc.structures.collections.Range;
import java.util.List;

public record Row(Range range, long offset) {

    public static Row parse(final List<Long> longs) {
        return new Row(
                new Range(longs.get(1), longs.get(1) + longs.get(2) - 1),
                longs.get(0) - longs.get(1)
        );
    }

    public boolean canMap(final long value) {
        return range.contains(value);
    }

    public boolean canMapBack(final long value) {
        return new Range(map(range().start()), map(range().end())).contains(value);
    }

    public long map(final long value) {
        return value + offset;
    }

    public long mapBack(final long value) {
        return value - offset;
    }
}
