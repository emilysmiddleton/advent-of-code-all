package esm.aoc.y23.d15;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.utils.Utils;

public class MainY23D15 {

    public static void main(String[] args) {
        final var lines = InputReader.readSeparated("2023/day15.txt", ",");
        final var part1 = lines.get(0).stream().mapToLong(Instruction::hash).sum();
        System.out.println("Part 1:" + part1);
        final var instructions = lines.get(0).stream()
                                      .map(l -> InputMapper.matchPattern(l, Instruction.PATTERN))
                                      .map(Instruction::parse)
                                      .toList();
        final var boxes = new Boxes();
        for (final var instruction : instructions) {
            boxes.apply(instruction);
        }
        final var part2 = Utils.sum(boxes.getPower().values());
        System.out.println("Part 2:" + part2);
    }


}
