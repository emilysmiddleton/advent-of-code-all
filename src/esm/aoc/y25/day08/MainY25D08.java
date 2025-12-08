package esm.aoc.y25.day08;

import java.util.List;
import java.util.Set;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.Coordinate3D;

public class MainY25D08 {

    public static void main(String[] args) {
        final List<Coordinate3D> input = InputReader.readLines("2025/day08.txt", Coordinate3D::parse);
        System.out.println(STR."Part 1: \{solve1(input)}");
        System.out.println(STR."Part 2: \{solve2(input)}");
    }

    private static int solve1(final List<Coordinate3D> input) {
        final Circuits circuits = new Circuits(input);
        for (int i = 0; i < 1000; i++) {
            circuits.addConnection();
        }
        final var list = circuits.getCircuits().stream().map(Set::size).sorted().toList().reversed();
        return list.get(0) * list.get(1) * list.get(2);
    }

    private static long solve2(final List<Coordinate3D> input) {
        final Circuits circuits = new Circuits(input);
        while (!circuits.isComplete()) {
            circuits.addConnection();
        }
        final var lastConnection = circuits.getLastConnection();
        return lastConnection.left().x() * lastConnection.right().x();
    }

}
