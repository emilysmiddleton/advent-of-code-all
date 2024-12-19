package esm.aoc.y24.d19;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import esm.aoc.input.InputReader;

public class MainY24D19 {

    public static void main(String[] args) throws InterruptedException {
        final var towels = InputReader.readSeparated("2024/day19_1.txt", ", ").getFirst();
        final var patterns = InputReader.readStrings("2024/day19_2.txt");
        System.out.println(STR."Part 1: \{solve1(towels, patterns)}");
        System.out.println(STR."Part 2: \{solve2(towels, patterns)}");
    }

    private static long solve1(final List<String> towels, final List<String> patterns) {
        final Map<String, Long> cache = new LinkedHashMap<>();
        return patterns.stream().mapToLong(p -> getCount(cache, towels, p)).filter(count -> count > 0).count();
    }

    private static long solve2(final List<String> towels, final List<String> patterns) {
        final Map<String, Long> cache = new LinkedHashMap<>();
        return patterns.stream().mapToLong(p -> getCount(cache, towels, p)).sum();
    }

    private static long getCount(final Map<String, Long> cache, final List<String> towels, final String pattern) {
        if (pattern.isEmpty()) {
            return 1;
        }
        if (cache.containsKey(pattern)) {
            return cache.get(pattern);
        }
        long count = 0;
        for (final var towel : towels) {
            if (pattern.startsWith(towel)) {
                count += getCount(cache, towels, pattern.substring(towel.length()));
            }
        }
        cache.put(pattern, count);
        return count;
    }

}
