package esm.aoc.y23.d12;

import esm.aoc.input.InputReader;

public class MainY23D12 {

    public static void main(String[] args) {
        final var solver = new LineSolver();
        final var part1 = solve(solver, false);
        System.out.println("Part 1: " + part1);
        final var part2 = solve(solver, true);
        System.out.println("Part 2: " + part2);
    }

    private static long solve(final LineSolver solver, final boolean expand) {
        final var input = InputReader.readSpaceSeparated("2023/day12.txt", parts -> Line.parse(parts, expand));
        long count = 0;
        for (final var line : input) {
            count += solver.solve(line);
        }
        return count;
    }

}
