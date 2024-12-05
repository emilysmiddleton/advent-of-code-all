package esm.aoc.y24.d05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.utils.Utils;

public class MainY24D05 {

    public static void main(String[] args) {
        final var rules = InputReader.readSeparated("2024/day05_1.txt", "\\|").stream().map(InputMapper::getIntegerList).toList();
        final var updates = InputReader.readSeparated("2024/day05_2.txt", ",").stream().map(InputMapper::getIntegerList).toList();
        System.out.println(STR."Part 1: \{solve1(rules, updates)}");
        System.out.println(STR."Part 2: \{solve2(rules, updates)}");
    }

    private static long solve1(final List<List<Integer>> rules, final List<List<Integer>> updates) {
        return updates.stream()
                .filter(update -> applyRules(rules, update))
                .mapToLong(Utils::getMiddle)
                .sum();
    }

    private static long solve2(final List<List<Integer>> rules, final List<List<Integer>> updates) {
        return updates.stream()
                .filter(update -> !applyRules(rules, update))
                .map(update -> sort(rules, update))
                .mapToLong(Utils::getMiddle)
                .sum();
    }

    private static List<Integer> sort(final List<List<Integer>> rules, final List<Integer> update) {
        final var comparator = getComparator(rules);
        final var copy = new ArrayList<>(update);
        copy.sort(comparator);
        return copy;
    }

    private static boolean applyRules(final List<List<Integer>> rules, final List<Integer> update) {
        return rules.stream().allMatch(rule -> applyRule(rule, update));
    }

    private static boolean applyRule(final List<Integer> rule, List<Integer> update) {
        final var left = update.indexOf(rule.getFirst());
        final var right = update.indexOf(rule.getLast());
        return left == -1 || right == -1 || left < right;
    }

    private static Comparator<Integer> getComparator(final List<List<Integer>> rules) {
        return (o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            }
            final var matchingRule = rules.stream().filter(rule -> rule.contains(o1) && rule.contains(o2)).findFirst().orElseThrow();
            return (o1.equals(matchingRule.getFirst())) ? -1 : 1;
        };
    }

}
