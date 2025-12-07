package esm.aoc.y25.day05;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Range;

public class MainY25D05 {

    public static void main(String[] args) {
        final List<List<String>> input = InputReader.readGroups("2025/day05.txt", s -> s);
        final List<Range> ranges = input.get(0).stream().map(Range::parse).toList();
        final List<Long> ids = input.get(1).stream().map(Long::parseLong).toList();
        System.out.println(STR."Part 1: \{solve1(ranges, ids)}");
        System.out.println(STR."Part 1: \{solve2(ranges)}");
    }

    private static long solve1(final List<Range> ranges, final List<Long> ids) {
        return ids.stream().filter(id -> isFresh(ranges, id)).count();
    }

    private static long solve2(final List<Range> ranges) {
        final Set<Range> merged = new LinkedHashSet<>();
        merged.add(ranges.getFirst());
        ranges.forEach(r -> mergeRange(merged, r));
        return merged.stream().mapToLong(Range::size).sum();
    }

    private static boolean isFresh(final List<Range> ranges, final long id) {
        return ranges.stream().anyMatch(r -> r.contains(id));
    }

    private static void mergeRange(final Set<Range> existing, final Range toAdd) {
        final List<Range> overLapping = existing.stream().filter(r -> r.overlaps(toAdd)).toList();
        if (overLapping.isEmpty()) {
            existing.add(toAdd);
            return;
        }
        overLapping.forEach(existing::remove);
        final long newMin = Math.min(overLapping.stream().map(Range::start).min(Long::compare).get(), toAdd.start());
        final long newMax = Math.max(overLapping.stream().map(Range::end).max(Long::compare).get(), toAdd.end());
        existing.add(new Range(newMin, newMax));
    }

}
