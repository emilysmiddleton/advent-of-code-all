package esm.aoc.y23.d05;

import esm.aoc.structures.collections.Range;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public record Group(List<Row> rows) {

    public long mapForward(final long value) {
        return rows.stream().filter(r -> r.canMap(value)).findFirst().map(r -> r.map(value)).orElse(value);
    }

    public long mapBackward(final long value) {
        return rows.stream().filter(r -> r.canMapBack(value)).findFirst().map(r -> r.mapBack(value)).orElse(value);
    }


    public Group combine(final Group other) {
        final Set<Long> points = new LinkedHashSet<>();
        points.addAll(getAllPoints());
        points.addAll(getAllPoints().stream().map(this::mapForward).toList());
        points.addAll(other.getAllPoints());
        points.addAll(other.getAllPoints().stream().map(this::mapBackward).toList());
        final List<Long> sorted = new ArrayList<>(points);
        Collections.sort(sorted);
        List<Row> newRows = new ArrayList<>();
        var currentPoint = sorted.get(0);
        var currentOffset = mapBoth(other, currentPoint) - currentPoint;
        for (int i = 1; i < sorted.size() - 1; i++) {
            final var nextPoint = sorted.get(i);
            final var nextOffset = mapBoth(other, nextPoint) - nextPoint;
            if (currentOffset != nextOffset) {
                newRows.add(new Row(new Range(currentPoint, nextPoint - 1), currentOffset));
                currentPoint = nextPoint;
                currentOffset = nextOffset;
            }
        }
        final var lastPoint = sorted.get(sorted.size() - 1);
        final var lastOffset = mapBoth(other, lastPoint) - lastPoint;
        if (lastOffset == currentOffset) {
            newRows.add(new Row(new Range(currentPoint, lastPoint), currentOffset));
        } else {
            newRows.add(new Row(new Range(currentPoint, lastPoint - 1), currentOffset));
            newRows.add(new Row(new Range(lastPoint, lastPoint), lastOffset));
        }
        return new Group(newRows);
    }

    private long mapBoth(final Group other, final long value) {
        return other.mapForward(mapForward(value));
    }

    private List<Long> getAllPoints() {
        final List<Long> result = new ArrayList<>();
        result.addAll(rows.stream().map(Row::range).map(Range::start).toList());
        result.addAll(rows.stream().map(Row::range).map(Range::end).toList());
        Collections.sort(result);
        result.add(0, result.get(0) - 1);
        result.add(result.get(result.size() - 1) + 1);
        return result;
    }

}
