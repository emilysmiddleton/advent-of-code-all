package esm.aoc.y23.d01;

import esm.aoc.input.InputReader;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainY23D01 {

    private static final String PART_ONE_NUMBER_PATTERN = "[0-9]";
    private static final String PART_TWO_NUMBER_PATTERN = "[0-9]|one|two|three|four|five|six|seven|eight|nine";
    private static final String PATTERN = ".*?(%s).*(%s).*|.*?(%s).*";
    public static void main(String[] args) {
        System.out.println("Part 1: " + solve(PART_ONE_NUMBER_PATTERN));
        System.out.println("Part 2: " + solve(PART_TWO_NUMBER_PATTERN));
    }

    private static int solve(final String numberPattern) {
        final Pattern pattern = Pattern.compile(String.format(PATTERN, numberPattern, numberPattern, numberPattern));
        return InputReader.readPattern("2023/day01.txt", pattern)
                          .stream()
                          .mapToInt(MainY23D01::toInteger)
                          .sum();
    }

    private static Integer toInteger(final List<String> strings) {
        return strings.stream()
                      .filter(Objects::nonNull)
                      .map(MainY23D01::parse)
                      .reduce((a, b) -> a + b)
                      .map(s -> s.length() == 1 ? s + s : s)
                      .map(Integer::parseInt)
                      .orElseThrow();
    }

    private static String parse(final String input) {
        return switch (input) {
            case "one", "1" -> "1";
            case "two", "2" -> "2";
            case "three", "3" -> "3";
            case "four", "4" -> "4";
            case "five", "5" -> "5";
            case "six", "6" -> "6";
            case "seven", "7" -> "7";
            case "eight", "8" -> "8";
            case "nine", "9" -> "9";
            default -> throw new IllegalStateException(input);
        };
    }

}
