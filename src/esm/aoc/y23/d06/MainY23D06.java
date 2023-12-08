package esm.aoc.y23.d06;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Range;
import esm.aoc.structures.collections.Utils;
import java.util.List;

public class MainY23D06 {

    public static void main(String[] args) {
        final var input = InputReader.readSeparated("2023/day06.txt", " +",
                s -> s.replace("Time:", "").replace("Distance:", "").trim());
        final var races = Utils.zip(input, MainY23D06::createRace);
        final var part1 = races.stream().mapToLong(MainY23D06::getWinningOptions).reduce((a, b) -> a * b).orElseThrow();
        System.out.println("Part 1: " + part1);
        final var race = new Race(concat(input.get(0)), concat(input.get(1)));
        final var part2 = getWinningOptions(race);
        System.out.println("Part 2: " + part2);
    }

    private static long getWinningOptions(final Race race) {
        final long firstWinning = new Range(1, race.time() - 1).findFirstMatchingPoint(race::winsIfHeldFor);
        final long firstLosing = new Range(firstWinning, race.time() - 1).findFirstMatchingPoint(race::losesIfHeldFor);
        return firstLosing - firstWinning;
    }

    private static long concat(final List<String> parts) {
        return Long.parseLong(parts.stream().reduce((a, b) -> a + b).orElseThrow());
    }

    private static Race createRace(final List<String> inputs) {
        return new Race(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)));
    }

}
