package esm.aoc.y25.day01;

import java.util.List;
import java.util.regex.Pattern;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.Direction;

public class MainY25D01 {

    private static final Pattern PATTERN = Pattern.compile("([LR])(\\d+)");

    public static void main(String[] args) {
        final var instructions = InputReader.readPattern("2025/day01.txt", PATTERN)
                .stream().map(MainY25D01::convertToNumber).toList();
        final Dial dial = new Dial();
        instructions.forEach(dial::turn);
        System.out.println(STR."Part 1: \{dial.stopsAtZeroCounter}");
        System.out.println(STR."Part 1: \{dial.pointsAtZeroCounter}");
    }

    private static Instruction convertToNumber(final List<String> sequence) {
        return new Instruction(Integer.parseInt(sequence.get(1)), Direction.parse(sequence.getFirst()));
    }

    private static final class Dial {
        private int pointer = 50;
        private int pointsAtZeroCounter;
        private int stopsAtZeroCounter;
        public void turn(final Instruction instruction) {
            for (int i = 0; i < instruction.value; i++) {
                pointer = (instruction.direction == Direction.LEFT ? pointer + 99 : pointer + 1) % 100;
                if (pointer == 0) {
                    pointsAtZeroCounter++;
                }
            }
            if (pointer == 0) {
                stopsAtZeroCounter++;
            }
        }
    }

    private record Instruction(int value, Direction direction) {
    }

}
