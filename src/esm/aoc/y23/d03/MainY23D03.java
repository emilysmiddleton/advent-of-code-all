package esm.aoc.y23.d03;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Multimap;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.EnginePart;
import esm.aoc.structures.grid.Grid;
import esm.aoc.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainY23D03 {

    public static void main(String[] args) {
        final var grid = InputReader.readGrid("2023/day03.txt", s -> s);
        final var parts = getEngineParts(grid);
        final var result1 = parts.stream()
                                .filter(EnginePart::nextToSymbol)
                                .mapToLong(EnginePart::getNumber)
                                .sum();
        System.out.println("Part 1: " + result1);
        final var gears = getGears(parts);
        final var result2 = gears
                .values()
                .stream()
                .filter(list -> list.size() > 1)
                .mapToLong(MainY23D03::gearRatio)
                .sum();
        System.out.println("Part 1: " + result2);
    }

    private static Multimap<Coordinate, EnginePart> getGears(final List<EnginePart> parts) {
        final Multimap<Coordinate, EnginePart> gears = new Multimap<>();
        for (final var part : parts) {
            part.getAdjacent().entrySet()
                .stream()
                .filter(e -> "*".equals(e.getValue()))
                .forEach(e -> gears.add(e.getKey(), part));
        }
        return gears;
    }

    private static List<EnginePart> getEngineParts(final Grid<String> grid) {
        final List<EnginePart> parts = new ArrayList<>();
        for (int y = grid.getMinY(); y <= grid.getMaxY(); y++) {
            EnginePart enginePart = new EnginePart();
            for (int x = grid.getMinX(); x <= grid.getMaxX(); x++) {
                String part = grid.get(x, y);
                if (part.matches("\\d")) {
                    enginePart.append(part, grid.getAdjacent(new Coordinate(x, y), true));
                } else {
                    if (enginePart.isNotEmpty()) {
                        parts.add(enginePart);
                    }
                    enginePart = new EnginePart();
                }
            }
            if (enginePart.isNotEmpty()) {
                parts.add(enginePart);
            }
        }
        return parts;
    }

    private static long gearRatio(final List<EnginePart> gears) {
        return Utils.productLong(gears.stream().map(EnginePart::getNumber).collect(Collectors.toList()));
    }

}
