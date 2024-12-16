package esm.aoc.y24.d16;

import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicLong;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.tree.DepthFirstIterator;

public class MainY24D16 {

    public static void main(String[] args) throws InterruptedException {
        final var grid = InputReader.readCharGrid("2024/day16.txt");
        System.out.println(STR."Part 1: \{solve1(grid)}");
        System.out.println(STR."Part 2: \{solve2()}");
    }

    private static long solve1(final CharGrid grid) {
        final var start = grid.filterByValue("S"::equals).iterator().next();
        final var rootNode = new RaceNode(new LinkedHashMap<>(), grid, new RaceNode.State(start, Direction.RIGHT, 0));
        AtomicLong min = new AtomicLong(Long.MAX_VALUE);
        final var iterator = new DepthFirstIterator<>(rootNode, state -> {
            if ("E".equals(grid.get(state.position()))) {
                min.set(Math.min(min.get(), state.score()));
            }
        });
        iterator.iterate();
        return min.get();
    }

    private static long solve2() {
        return 0;
    }


}
