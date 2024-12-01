package esm.aoc.y24.d01;

import java.util.List;
import java.util.regex.Pattern;

import esm.aoc.input.InputReader;

public class MainY24D01 {

    private static final Pattern PATTERN = Pattern.compile("([0-9]{5}) {3}([0-9]{5})");

    public static void main(String[] args) {
        final var lines = InputReader.readPattern("2024/day01.txt", PATTERN);
        final var left = lines.stream().map(List::getFirst).map(Integer::parseInt).sorted().toList();
        final var right = lines.stream().map(list -> list.get(1)).map(Integer::parseInt).sorted().toList();
        System.out.println(STR."Part 1: \{solve1(left, right)}");
        System.out.println(STR."Part 2: \{solve2(left, right)}");
    }

    private static int solve1(final List<Integer> left, final List<Integer> right) {
        int sum = 0;
        for (int i = 0; i < left.size(); i++) {
            sum += Math.abs(right.get(i) - left.get(i));
        }
        return sum;
    }

    private static int solve2(final List<Integer> left, final List<Integer> right) {
        int sum = 0;
        for (final var leftNum : left) {
            final int score = (int) right.stream().filter(leftNum::equals).count();
            sum += score * leftNum;
        }
        return sum;
    }

}
