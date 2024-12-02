package esm.aoc.y24.d02;

import java.util.ArrayList;
import java.util.List;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.combinations.Combinations;

public class MainY24D02 {

    public static void main(String[] args) {
        final var reports = InputReader.readSpaceSeparated("2024/day02.txt", InputMapper::getIntegerList);
        System.out.println(STR."Part 1: \{solve1(reports)}");
        System.out.println(STR."Part 2: \{solve2(reports)}");
    }

    private static long solve1(final List<List<Integer>> reports) {
        return reports.stream().filter(MainY24D02::isSafe).count();
    }

    private static long solve2(final List<List<Integer>> reports) {
        return reports.stream().filter(MainY24D02::anySafe).count();
    }

    private static boolean anySafe(final List<Integer> levels) {
        if (isSafe(levels)) {
            return true;
        }
        for (int i = 0; i < levels.size(); i++) {
            if (isSafe(removeOne(levels, i))) {
                return true;
            }
        }
        return false;
    }

    private static List<Integer> removeOne(final List<Integer> levels, final int toRemove) {
        final var copy = new ArrayList<>(levels);
        copy.remove(toRemove);
        return copy;
    }

    private static boolean isSafe(final List<Integer> levels) {
        final boolean ascending = levels.getFirst() < levels.getLast();
        return Combinations.getPairs(levels).stream()
                .map(pair -> ascending ? pair.right() - pair.left() : pair.left() - pair.right())
                .allMatch(diff -> diff >= 1 && diff <= 3);
    }

}
