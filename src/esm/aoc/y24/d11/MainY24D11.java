package esm.aoc.y24.d11;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Pair;

public class MainY24D11 {

    public static void main(String[] args) {
        final var stones = InputMapper.getLongList(InputReader.readSpaceSeparated("2024/day11.txt").getFirst());
        System.out.println(STR."Part 1: \{solve(stones, 25)}");
        System.out.println(STR."Part 2: \{solve(stones, 75)}");
    }

    private static long solve(final List<Long> stones, final int repeat) {
        Map<Pair<Long, Integer>, Long> cache = new LinkedHashMap<>();
        long sum = 0;
        for (final var stone : stones) {
            sum += blink(cache, stone, repeat);
        }
        return sum;
    }

    private static long blink(Map<Pair<Long, Integer>, Long> cache, final long stone, final int n) {
        final var key = new Pair<>(stone, n);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (n == 0) {
            return 1;
        }
        if (stone == 0) {
            final var result =  blink(cache, 1, n - 1);
            cache.put(key, result);
            return result;
        }
        final var asString = Long.toString(stone);
        final var length = asString.length();
        if (length % 2 == 0) {
            final var result =  blink(cache, Long.parseLong(asString.substring(0, length / 2)), n - 1) +
                                blink(cache, Long.parseLong(asString.substring(length / 2)), n - 1);
            cache.put(key, result);
            return result;
        }
        final var result = blink(cache, stone * 2024, n - 1);
        cache.put(key, result);
        return result;
    }

}
