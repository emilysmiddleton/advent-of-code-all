package esm.aoc.y24.d14;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import esm.aoc.input.InputReader;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.utils.Utils;

public class MainY24D14 {

    private static final Pattern PATTERN = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");
    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;
    private static final int MIDDLE_Y = (HEIGHT) / 2;
    private static final int MIDDLE_X = (WIDTH) / 2;

    // 230461440
    // 238081536
    public static void main(String[] args) throws InterruptedException {
        final var robots = InputReader.readPattern("2024/day14.txt", PATTERN).stream().map(MainY24D14::parse).collect(Collectors.toSet());
        System.out.println(STR."Part 1: \{solve1(robots)}");
        System.out.println(STR."Part 2: \{solve2(robots)}");
    }

    private static long solve1(final Set<Robot> robots) {
        Map<Direction, Integer> counts = new LinkedHashMap<>();
        var next = robots;
        for (int i = 1; i <= 100; i++) {
            next = next.stream().map(Robot::move).collect(Collectors.toSet());
        }
        for (final var robot : next) {
            final var quadrant = robot.getQuadrant();
            quadrant.ifPresent(direction -> counts.put(direction, counts.getOrDefault(direction, 0) + 1));
        }
        return Utils.product(counts.values());
    }

    private static long solve2(final Set<Robot> robots) {
        Map<Direction, Integer> counts = new LinkedHashMap<>();
        var next = robots;
        for (int i = 1; i < 10000; i++) {
            next = next.stream().map(Robot::move).collect(Collectors.toSet());
            final var coordinates = next.stream().map(Robot::getPosition).collect(Collectors.toSet());
            if (couldBeATree(coordinates)) {
                printGrid(coordinates);
                return i;
            }
        }
        return 0;
    }

    private static void printGrid(final Set<Coordinate> coordinates) {
        final Map<Coordinate, String> map = new LinkedHashMap<>();
        coordinates.forEach(c -> map.put(c, "X"));
        final var grid = new CharGrid(map);
        System.out.println(grid);
        System.out.println("-".repeat(WIDTH));
    }

    private static Robot parse(final List<String> parts) {
        final var px = Long.parseLong(parts.get(0));
        final var py = Long.parseLong(parts.get(1));
        final var vx = Long.parseLong(parts.get(2));
        final var vy = Long.parseLong(parts.get(3));
        return new Robot(
                new WrappingCoordinate(new Coordinate(px, py), WIDTH, HEIGHT),
                new Coordinate(vx, vy)
        );
    }

    record Robot(WrappingCoordinate position, Coordinate velocity) {
        private Robot move() {
            return new Robot(position.add(velocity), velocity);
        }
        private Optional<Direction> getQuadrant() {
            if (position.x() == MIDDLE_X || position.y() == MIDDLE_Y) {
                return Optional.empty();
            }
            if (position.x() < MIDDLE_X) {
                return Optional.of(position.y() < MIDDLE_Y ? Direction.DOWN_LEFT : Direction.UP_LEFT);
            }
            return Optional.of(position.y() < MIDDLE_Y ? Direction.DOWN_RIGHT : Direction.UP_RIGHT);
        }

        Coordinate getPosition() {
            return position.getPosition();
        }
    }

    private static boolean couldBeATree(final Set<Coordinate> coordinates) {
        // Look for horizontal lines.
        int biggestLine = 0;
        for (final var coordinate : coordinates) {
            var left = coordinate;
            int leftScore = 1;
            while (coordinates.contains(left)) {
                left = left.move(Direction.LEFT);
                leftScore++;
            }
            biggestLine = Math.max(biggestLine, leftScore);
            var right = coordinate;
            int rightScore = 1;
            while (coordinates.contains(right)) {
                right = right.move(Direction.RIGHT);
                rightScore++;
            }
            biggestLine = Math.max(biggestLine, rightScore);
        }
        return biggestLine > 8;
    }

}
