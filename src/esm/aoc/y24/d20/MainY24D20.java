package esm.aoc.y24.d20;

import java.util.Collections;
import java.util.List;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.tree.Pathfinder;

public class MainY24D20 {

    private static final int MIN_SAVING = 100;

    public static void main(String[] args) throws InterruptedException {
        final var grid = InputReader.readCharGrid("2024/day20.txt");
        System.out.println(STR."Part 1: \{solve1(grid)}");
        System.out.println(STR."Part 2: \{solve2()}");
    }

    private static long solve1(final CharGrid grid) {
        final var path = getPath(grid);
        long count = 0;
        for (int i = 0; i < path.size(); i++) {
            final var coordinate = path.get(i);
            for (final var direction : Direction.getValues(false)) {
                final var one = coordinate.move(direction);
                final var two = one.move(direction);
                if ("#".equals(grid.get(one)) && path.contains(two)) {
                    final var saving = path.indexOf(two) - i - 2;
                    if (saving >= MIN_SAVING) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static List<Coordinate> getPath(final CharGrid grid) {
        Coordinate startPosition = grid.filterByValue("S"::equals).stream().findFirst().orElseThrow();
        Coordinate endPosition = grid.filterByValue("E"::equals).stream().findFirst().orElseThrow();
        final var pathFinder = new Pathfinder(grid, startPosition, Collections.singleton("."), endPosition::equals);
        return pathFinder.findPath(Integer.MAX_VALUE).orElseThrow();
    }

    private static long solve2() {
        return 0;
    }

}
