package esm.aoc.y24.d03;

import java.util.regex.Pattern;

import esm.aoc.input.FileReader;
import esm.aoc.input.InputMapper;

public class MainY24D03 {

    private static final Pattern MUL_PATTERN = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile(STR."\{MUL_PATTERN.pattern()}|do\\(\\)|don't\\(\\)");

    public static void main(String[] args) {
        final var input = String.join("", FileReader.readStrings("2024/day03.txt"));
        System.out.println(STR."Part 1: \{solve1(input)}");
        System.out.println(STR."Part 2: \{solve2(input)}");
    }

    private static long solve1(final String input) {
        return InputMapper.parseMatches(input, MUL_PATTERN).stream().mapToLong(MainY24D03::multiply).sum();
    }

    private static long solve2(final String input) {
        boolean enabled = true;
        long sum = 0;
        for (final var instruction : InputMapper.parseMatches(input, INSTRUCTION_PATTERN)) {
            switch (instruction) {
                case "do()": enabled = true; break;
                case "don't()": enabled = false; break;
                default: sum += enabled ? multiply(instruction) : 0;
            }
        }
        return sum;
    }

    private static long multiply(final String instruction) {
        final var matcher = MUL_PATTERN.matcher(instruction);
        if (matcher.matches()) {
            return Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
        }
        throw new IllegalStateException();
    }

}
