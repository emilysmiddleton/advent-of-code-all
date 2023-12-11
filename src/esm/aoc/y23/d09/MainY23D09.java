package esm.aoc.y23.d09;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.combinations.Combinations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainY23D09 {

    public static void main(String[] args) {
        final var input = InputReader.readSpaceSeparated("2023/day09.txt", InputMapper::getLongList);
        final var part1 = input.stream().mapToLong(MainY23D09::getNextInSequence).sum();
        final var part2 = input.stream().mapToLong(MainY23D09::getPreviousInSequence).sum();
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    private static long getNextInSequence(final List<Long> input) {
        final List<List<Long>> diffs = getDiffs(input);
        long value = 0;
        for (int i = diffs.size() - 2; i >= 0; i--) {
            final var diff = diffs.get(i);
            final var last = diff.get(diff.size() - 1);
            value += last;
        }
        return value;
    }

    private static long getPreviousInSequence(final List<Long> input) {
        final List<List<Long>> diffs = getDiffs(input);
        long value = 0;
        for (int i = diffs.size() - 2; i >= 0; i--) {
            final var diff = diffs.get(i);
            final var last = diff.get(0);
            value = last - value;
        }
        return value;
    }

    private static List<List<Long>> getDiffs(final List<Long> input) {
        final List<List<Long>> diffs = new ArrayList<>();
        diffs.add(input);
        var diff = Combinations.getPairs(input).stream().map(p -> p.right() - p.left()).toList();
        diffs.add(diff);
        while (new LinkedHashSet<>(diff).size() > 1 || diff.get(0) != 0) {
            diff = Combinations.getPairs(diff).stream().map(p -> p.right() - p.left()).toList();
            diffs.add(diff);
        }
        return diffs;
    }

}
