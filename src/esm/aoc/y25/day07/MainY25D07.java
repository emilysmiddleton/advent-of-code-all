package esm.aoc.y25.day07;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;

public class MainY25D07 {

    public static void main(String[] args) {
        final CharGrid grid = InputReader.readCharGrid("2025/day07.txt");
        final var tachynon = new Tachyon(grid);
        System.out.println(tachynon.getSplittersHit());
        System.out.println(tachynon.getAlternateTimelines());
    }

}
