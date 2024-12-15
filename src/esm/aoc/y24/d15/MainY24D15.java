package esm.aoc.y24.d15;

import java.util.Collection;
import java.util.List;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.Direction;

public class MainY24D15 {

    public static void main(String[] args) throws InterruptedException {
        final var grid = InputReader.readCharGrid("2024/day15_1.txt");
        final var directions = InputReader.readSeparated("2024/day15_2.txt", "").stream().flatMap(Collection::stream).map(Direction::parse).toList();
        System.out.println(STR."Part 1: \{solve(new Warehouse(grid, "O", "O"), directions)}");
        System.out.println(STR."Part 2: \{solve(new DoubleWidthGrid(grid), directions)}");
    }

    private static long solve(final Warehouse grid, List<Direction> directions) {
        directions.forEach(grid::move);
        System.out.println(grid.grid);
        return grid.gps();
    }

}
