package esm.aoc.y23.d16;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.tree.DepthFirstIterator;
import esm.aoc.structures.tree.LazyTreeNode;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        final var grid = InputReader.readCharGrid("2023/day16.txt");
        final var part1 = solvePart1(grid);
        System.out.println("Part 1: " + part1);
        final var part2 = solvePart2(grid);
        System.out.println("Part 2: " + part2);
    }

    private static int solvePart1(final CharGrid grid) {
        return solve(grid, new State(new Coordinate(-1, grid.getMaxY()), Direction.RIGHT));
    }

    private static int solvePart2(final CharGrid grid) {
        int max = Integer.MIN_VALUE;
        for (long x = grid.getMinX(); x <= grid.getMaxX(); x++) {
            max = Math.max(max, solve(grid, new State(new Coordinate(x, -1), Direction.UP)));
            max = Math.max(max, solve(grid, new State(new Coordinate(x, grid.getMaxY() + 1), Direction.DOWN)));
        }
        for (long y = grid.getMinY(); y <= grid.getMaxY(); y++) {
            max = Math.max(max, solve(grid, new State(new Coordinate(-1, y), Direction.RIGHT)));
            max = Math.max(max, solve(grid, new State(new Coordinate(grid.getMaxX() + 1, y), Direction.LEFT)));
        }
        return max;
    }

    private static int solve(final CharGrid grid, final State start) {
        final var function = new NextStateFunction(grid);
        final var startNode = new LazyTreeNode<>(function, start);
        final var iterator = new DepthFirstIterator<>(startNode);
        while (iterator.hasNext()) {
            iterator.next();
        }
        return function.getVisited().stream().map(State::position).collect(Collectors.toSet()).size() - 1;
    }
}
