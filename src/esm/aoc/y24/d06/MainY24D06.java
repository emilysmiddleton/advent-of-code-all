package esm.aoc.y24.d06;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;

public class MainY24D06 {

    public static void main(String[] args) {
        System.out.println(STR."Part 1: \{solve1()}");
        System.out.println(STR."Part 2: \{solve2()}");
    }

    private static long solve1() {
        final var guardPath = getGuardPath();
        completePath(guardPath);
        return guardPath.getPath().stream().map(Pair::left).count() - 1;
    }

    private static long solve2() {
        final GuardPath guardPath = getGuardPath();
        completePath(guardPath);
        final var possibilities = guardPath.getSpacesInPath();
        long sum = 0;
        for (final var space : possibilities) {
            final var copy = getGuardPath();
            copy.setObstacle(space);
            completePath(copy);
            if (copy.isLoop()) {
                sum++;
            }
        }
        return sum;
    }

    private static void completePath(final GuardPath guardPath) {
        while (guardPath.hasNext()) {
            guardPath.next();
        }
    }

    private static GuardPath getGuardPath() {
        final var grid = InputReader.readCharGrid("2024/day06.txt");
        final Coordinate start = grid.getCoordinates().stream().filter(c -> "^".equals(grid.get(c))).findFirst().orElseThrow();
        grid.put(start, ".");
        return new GuardPath(grid, start, Direction.UP);
    }

}
