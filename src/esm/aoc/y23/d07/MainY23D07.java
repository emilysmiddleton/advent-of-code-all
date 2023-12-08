package esm.aoc.y23.d07;

import esm.aoc.input.InputReader;
import java.util.List;
import java.util.function.Function;

public class MainY23D07 {

    public static void main(String[] args) {
        final long part1 = solve(Hand::parse);
        final var part2 = solve(Hand::parseWithJokers);
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    private static long solve(final Function<List<String>, Hand> parser) {
        final var hands = InputReader.readSpaceSeparated("2023/day07.txt")
                                     .stream()
                                     .map(parser)
                                     .sorted()
                                     .toList();
        long sum = 0;
        for (int i = 1; i <= hands.size(); i++) {
            sum += hands.get(i - 1).bid() * i;
        }
        return sum;
    }

}
