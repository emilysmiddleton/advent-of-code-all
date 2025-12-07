package esm.aoc.y25.day02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Range;

public class MainY25D02 {

    public static void main(String[] args) {
        final List<Range> inputs = InputReader.readSeparated("2025/day02.txt", ",")
            .getFirst()
            .stream()
            .map(MainY25D02::convert)
            .toList();
        System.out.println(solve(inputs, MainY25D02::isInvalid));
        System.out.println(solve(inputs, MainY25D02::isInvalid2));
    }

    private static long solve(final List<Range> inputs, final Predicate<Long> invalidTester) {
        long sum = 0;
        for (final var range : inputs) {
            for (long i = range.start(); i <= range.end(); i++) {
                if (invalidTester.test(i)) {
                    sum += i;
                }
            }
        }
        return sum;
    }

    private static Range convert(final String input) {
        final var split = input.split("-");
        return new Range(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

    private static boolean isInvalid(final long value) {
        String asString = String.valueOf(value);
        if (asString.length() % 2 != 0) {
            return false;
        }
        return hasRepeatOfLength(asString, asString.length() / 2);
    }

    private static boolean isInvalid2(final long value) {
        String asString = String.valueOf(value);
        final int length = asString.length();
        for (int i = 1; i < length; i++) {
            if (hasRepeatOfLength(asString, i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasRepeatOfLength(final String input, final int length) {
        if (input.length() % length != 0) {
            return false;
        }
        final List<String> splitByLength = splitByLength(input, length);
        return new HashSet<>(splitByLength).size() == 1;
    }

    private static List<String> splitByLength(final String input, final int length) {
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i += length) {
            result.add(input.substring(i, Math.min(i + length, input.length())));
        }
        return result;
    }


}
