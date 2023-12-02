package esm.aoc.y15.d25;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.Coordinate;

public class MainY15D25 {

    public static void main(String[] args) {
        final var coordinates = InputReader.readSeparated("2015/day25.txt", " ").get(0);
        final var coordinate = new Coordinate(
                Integer.parseInt(coordinates.get(0)) - 1,
                Integer.parseInt(coordinates.get(1)) - 1
        );
        final int index = getNumberAtCoordinate(coordinate);
        System.out.println(getValue(index));
    }

    private static long getValue(final int index) {
        long value = 20151125;
        for (int i = 1; i < index; i++) {
            value = (value * 252533) % 33554393;
        }
        return value;
    }

    private static int getNumberAtCoordinate(final Coordinate coordinate) {
        final var iterator = new CoordinateIterator(new Coordinate(0, 0));
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            final var next = iterator.next();
            if (next.equals(coordinate)) {
                return counter;
            }
        }
        throw new IllegalStateException();
    }
}
