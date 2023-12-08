package esm.aoc.y23.d05;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Range;
import java.util.List;
import java.util.stream.Collectors;

public class MainY23D05 {

    public static void main(String[] args) {
        final var seeds = InputReader.readSpaceSeparated("2023/day05_seeds.txt", InputMapper::getLongList).get(0);
        final var groups = InputReader.readGroups("2023/day05.txt", MainY23D05::parseGroup);
        final var combined = groups.stream().reduce(Group::combine).orElseThrow();
        final var part1 = seeds.stream().mapToLong(combined::mapForward).min().orElseThrow();
        System.out.println("Part 1: " + part1);
        var part2 = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size() - 2; i+=2) {
            final var seedRange = new Range(seeds.get(i), seeds.get(i) + seeds.get(i + 1) - 1);
            final var overlaps = combined.rows().stream().filter(row -> seedRange.overlaps(row.range())).collect(Collectors.toSet());
            for (final Row row : overlaps) {
                final var overlap = seedRange.getOverlap(row.range());
                part2 = Math.min(part2, overlap.start() + row.offset());
                part2 = Math.min(part2, overlap.end() + row.offset());
            }
        }
        System.out.println("Part 2: " + part2);
    }

    public static Group parseGroup(final List<String> group) {
        final List<Row> rows = group.subList(1, group.size())
                               .stream()
                               .map(line -> InputMapper.getLongList(InputMapper.getSeparatedStrings(line, " ")))
                               .map(Row::parse)
                               .toList();
        return new Group(rows);
    }

}
